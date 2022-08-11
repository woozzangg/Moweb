<template>
  <div id="main-container" class="container">
    <div id="session" v-if="session">
      <video v-show="false" ref="input_video"></video>
      <div id="session-header">
        <h1 id="session-title">{{ room_no }}</h1>
        <v-btn id="buttonLeaveSession" @click="leaveBtn"> 나가기 </v-btn>
      </div>

      <v-btn @click="cameraBtnHandler">
        {{ cameraBtnTxt }}
      </v-btn>
      <v-btn @click="micBtnHandler">
        {{ micBtnTxt }}
      </v-btn>
      <v-container>
        <canvas
          v-show="false"
          class="output_canvas"
          id="output_canvas"
          :width="width"
          :height="height"
          style="transform: rotateY(180deg)"
        ></canvas>
        <v-row>
          <v-col>
            <user-video v-if="videoSetting" :stream-manager="publisher" />
          </v-col>
          <v-col
            v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
          >
            <user-video :stream-manager="sub" />
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../components/UserVideo";
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7a507.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "photoView",

  components: {
    UserVideo,
  },
  computed: {
    inputVideo() {
      return this.$refs.input_video;
    },
  },
  data() {
    return {
      user_name: "",
      room_no: "",
      url: "",
      is_admin: undefined,

      OV: undefined,
      session: undefined,
      publisher: undefined,
      subscribers: [],

      width: 0,
      height: 0,
      camera: undefined,
      cameraOn: "true",
      cameraBtnTxt: "camera off",
      micOn: "true",
      micBtnTxt: "mic off",
      videoSetting: false,
      canvasStream: undefined,
    };
  },
  created() {
    this.is_admin = this.$route.params.is_admin;
    this.user_name = this.$route.params.user_name;
    this.room_no = this.$route.params.room_no + "";
    this.url = this.$route.params.url;
  },
  mounted() {
    this.joinSession();
  },
  methods: {
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.getToken(this.room_no).then(async (token) => {
        await this.removeBG();
        this.session
          .connect(token, { clientData: this.user_name })
          .then(async () => {
            let canvas = document.getElementById("output_canvas");
            this.canvasStream = canvas.captureStream(30);
            let videoTracks = this.canvasStream.getVideoTracks();
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: videoTracks[0], // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
            this.publisher = publisher;

            this.session.publish(this.publisher);
          })
          .then(() => {
            this.videoSetting = true;
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
      window.addEventListener("popstate", this.leaveSession);
    },
    leaveBtn() {
      if (this.is_admin) {
        alert("방장이 나가면 방이 사라집니다.");
      }
      if (confirm("정말로 나가시겠습니까?")) this.leaveSession();
    },
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      this.videoSetting = false;
      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.replace("/");
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
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
        width: 320,
        height: 240,
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

      // 배경 그리기
      this.ctx.globalCompositeOperation = "source-out";
      this.ctx.fillStyle = "#009933"; // 배경색 부분
      this.ctx.fillRect(0, 0, results.image.width, results.image.height);

      // Only overwrite missing pixels.
      this.ctx.globalCompositeOperation = "destination-atop";
      await this.ctx.drawImage(
        results.image,
        0,
        0,
        results.image.width,
        results.image.height
      );

      this.ctx.restore();
    },
    // 카메라 on/off
    async cameraBtnHandler() {
      this.cameraOn = !this.cameraOn;

      if (this.cameraOn) {
        this.cameraBtnTxt = "camera off";
        this.camera.start();
      } else {
        this.cameraBtnTxt = "camera on";
        this.camera.stop();
        setTimeout(() => {
          this.ctx.clearRect(0, 0, this.width, this.height);
          this.ctx.fillStyle = "#009933";
          this.ctx.fillRect(0, 0, this.width, this.height);
          this.ctx.restore();
        }, 100);
      }
    },
    // 마이크 on/off
    micBtnHandler() {
      this.micOn = !this.micOn;
      this.publisher.publishAudio(this.micOn);
      if (this.micOn) {
        this.micBtnTxt = "mic off";
      } else {
        this.micBtnTxt = "mic on";
      }
    },
  },
};
</script>
<style scoped>
video {
  transform: rotateY(180deg);
}
body {
  background-color: rgb(255, 255, 255);
}
</style>
