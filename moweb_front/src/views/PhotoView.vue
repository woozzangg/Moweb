<template>
  <div>
    <video v-show="false" ref="input_video"></video>
    <canvas
      class="output_canvas"
      id="output_canvas"
      :width="width"
      :height="height"
    ></canvas>
    <button @click="cameraBtnHandler">
      {{ cameraBtnTxt }}
    </button>
    <button @click="micBtnHandler">
      {{ micBtnTxt }}
    </button>
    <WebrtcVideo ref="my"></WebrtcVideo>
    <WebrtcVideo ref="join_user"> </WebrtcVideo>

    <input v-model="myId" />

    <button @click="connect()">방입장</button>
  </div>
</template>

<script>
import WebrtcVideo from "@/components/WebrtcVideo.vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import Peer from "peerjs";
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";

let socket;
let stomp;

export default {
  name: "HomeView",
  components: {
    WebrtcVideo,
  },
  data() {
    return {
      myId: "",
      callerStream: "",
      peers: [],
      users: [],
      ctx: null,
      width: 400,
      height: 400,
      peer: new Peer(),
      signal: "",
      room_no: 0,
      camera: null,
      cameraOn: "true",
      cameraBtnTxt: "camera off",
      micOn: "true",
      micBtnTxt: "mic off",
    };
  },
  mounted() {
    this.init();
  },
  computed: {
    inputVideo() {
      return this.$refs.input_video;
    },
  },
  methods: {
    init() {
      this.peer.on("open", (signal) => {
        this.signal = signal;
        console.log("mysignal : ", signal);
      });
      this.userSet();
    },

    // 스트림 생성
    async userSet() {
      await this.removeBG();
      await navigator.mediaDevices
        .getUserMedia({
          audio: true,
        })
        .then((stream) => {
          let canvas = document.getElementById("output_canvas");
          let canvasStream = canvas.captureStream(30);
          let videoStream = new MediaStream(canvasStream.getVideoTracks());
          videoStream.addTrack(stream.getAudioTracks()[0]);
          this.callerStream = videoStream;
          this.$refs["my"].$refs["video"].srcObject = this.canvasStream;
        });
    },
    // socket stomp 연결
    connect() {
      socket = new SockJS("http://localhost:9999/socket");
      stomp = Stomp.over(socket);
      stomp.debug = function () {};
      stomp.connect(
        {},
        // connectCallback
        () => {
          stomp.subscribe("/topic/room/" + this.room_no, (jsonData) => {
            let data = JSON.parse(jsonData.body);
            let action = data.action;
            switch (action) {
              case 10:
                // 누군가 join 했을때
                this.joinedRoom(data.sessionIdList);
                break;
              case 11:
                // 소켓으로 caller의 연락을 받은 시점에 caller의 정보를 저장한다.
                this.newUserJoin(data);
                break;
              case 12:
                // acceptCall을 받은 시점에서 caller와 callee를 연결.
                this.callAnswer(data);
                break;
            }
          });

          //close session event
          stomp.subscribe("/topic/video/close-session", (data) => {
            // 세션을 나갔을때 관련된 peer을 다 remove해준다.
            let closedUser = String(JSON.parse(data.body));
            this.connectingState[closedUser] = "before";
            // peers 목록에서 삭제.
            let i = 0;
            while (i < this.peers.length) {
              if (
                this.peers[i][1] === closedUser ||
                this.peers[i][2] === closedUser
              ) {
                this.peers[i][0].destroy();
                this.peers.splice(i, 1);
              } else {
                i++;
              }
            }
          });
          // socket join send
          stomp.send(
            "/app/video/joined-room-info",
            JSON.stringify({
              room_no: this.room_no,
              user_name: this.myId,
            })
          );
        },
        // onErrorCallback
        () => {
          console.log("ws error");
        }
      );
    },

    joinedRoom(datas) {
      console.log(datas);
      if (datas[datas.length - 1].user_name != this.myId) return;

      // 새로 입장한 사람이 본인이면 기존 참가자들에게 sinal정보를 보냄.
      for (let data of datas) {
        if (data.user_name == this.myId) continue;
        this.sendSignal();
      }
    },

    // 새로운 유저에게 스트림 요청
    newUserJoin(data) {
      if (this.users.includes(data.user_name)) return;
      // 연결한 유저가 아닐경우
      console.log("newUser", data);
      this.initCall(data);
    },

    callAnswer(data) {
      if (data.user_name != this.myId || data.signal == this.signal) return;
      console.log("call-answer", data);
      this.returnCall(data);
    },

    // 자신의 signal을 보냄
    sendSignal() {
      stomp.send(
        "/app/video/signal",
        JSON.stringify({
          user_name: this.myId,
          signal: this.signal,
          room_no: this.room_no,
        })
      );
    },

    initCall(data) {
      const peer = this.peer;
      const call = peer.call(data.signal, this.callerStream);
      call.on("stream", (stream) => {
        this.$refs["join_user"].$refs["video"].srcObject = stream;
      });

      stomp.send(
        "/app/video/callAnswer",
        JSON.stringify({
          user_name: data.user_name,
          room_no: this.room_no,
          signal: this.signal,
        })
      );
      this.users.push(data.user_name);
    },

    // caller에게 요청을 받은 상태에서 connect answer을 보냄.
    returnCall(data) {
      console.log("return call", data);
      const peer = this.peer;

      peer.on("call", (call) => {
        call.answer(this.callerStream);
        call.on("stream", (stream) => {
          this.$refs["join_user"].$refs["video"].srcObject = stream;
        });
      });
    },

    // 배경제거 해서 그리기
    async removeBG() {
      this.ctx = document.getElementById("output_canvas").getContext("2d");
      const selfieSegmentation = new SelfieSegmentation({
        locateFile: (file) => {
          return `https://cdn.jsdelivr.net/npm/@mediapipe/selfie_segmentation/${file}`;
        },
      });
      selfieSegmentation.setOptions({
        modelSelection: 1,
      });
      selfieSegmentation.onResults(this.onResults);

      this.camera = new Camera(this.inputVideo, {
        onFrame: async () => {
          await selfieSegmentation.send({ image: this.inputVideo });
        },
        width: 400,
        height: 400,
      });
      this.camera.start();
      return 1;
    },

    // 배경제거 옵션
    async onResults(results) {
      this.width = results.image.width;
      this.height = results.image.height;
      this.ctx.save();
      this.ctx.clearRect(0, 0, results.image.width, results.image.height);
      this.ctx.drawImage(
        results.segmentationMask,
        0,
        0,
        results.image.width,
        results.image.height
      );

      // Only overwrite existing pixels.
      this.ctx.globalCompositeOperation = "source-out";
      this.ctx.fillStyle = "rgba(0,255,0,1)";
      this.ctx.fillRect(0, 0, results.image.width, results.image.height);

      // Only overwrite missing pixels.
      this.ctx.globalCompositeOperation = "source-out";
      this.ctx.drawImage(
        results.image,
        0,
        0,
        results.image.width,
        results.image.height
      );

      this.ctx.restore();
    },
    cameraBtnHandler() {
      this.cameraOn = !this.cameraOn;
      this.callerStream.getVideoTracks().forEach((track) => {
        track.enabled = !track.enabled;
      });
      if (this.cameraOn) {
        this.cameraBtnTxt = "camera off";
        this.camera.start();
      } else {
        this.cameraBtnTxt = "camera on";
        this.camera.stop();
      }
    },
    micBtnHandler() {
      this.micOn = !this.micOn;
      this.callerStream.getAudioTracks().forEach((track) => {
        track.enabled = !track.enabled;
      });
      if (this.micOn) {
        this.micBtnTxt = "mic off";
      } else {
        this.micBtnTxt = "mic on";
      }
    },
  },
};
</script>

<style></style>
