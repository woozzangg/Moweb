<template>
  <!-- 컨테이너 시작 -->
  <v-container
    class="border-style2"
    style="margin: 20px auto 0px auto; padding: 0px"
  >
    <!-- row로 구간 나눔 -->

    <!-- 캠에서 가져온 소스비디오 -->
    <video v-show="false" ref="input_video"></video>

    <!-- webrtc를 통해 보낼 소스 -->
    <canvas
      v-show="false"
      class="output_canvas"
      id="output_canvas"
      :width="width"
      :height="height"
      style="transform: rotateY(180deg)"
    ></canvas>
    <v-row d-flex fluid justify-space-around style="margin: 0px">
      <!-- 왼쪽 영역 -->
      <v-col
        no-gutters
        fluid
        cols="8"
        class="border-style1"
        style="margin: 10px auto 0px auto"
      >
        <!-- //////////// 여기서 v-if나 v-show로  if else 걸어서 두기 -->
        <!-- ///////// 정상화면 row 9 , 2 -->
        <!-- # WebRTC 화면 부분 -->
        <!-- <v-row
          no-gutters
          rows="9"
          fluid
          class="border-style1"
          style="margin: 5px"
        >
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #00ff00; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #004400; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #f00; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          WebRTC 화면이 들어올 곳
        </v-row>

        <v-row
          fluid
          no-gutters
          rows="2"
          class="border-style1"
          style="margin: 4px"
        >
          버튼 모음집
          <v-btn elevation="9" outlined tile rounded>
            <router-link to="/shot" style="margin: 10px">shot으로</router-link>

            <router-view />
          </v-btn>
          <v-btn class="pink white--text">
            <router-link to="/waiting" style="margin: 10px"
              >waiting으로</router-link
            >
            |
          </v-btn>
        </v-row> -->

        <!-- 촬영화면 들어가는곳 -->
        <!-- # 배경선택 들어갔을때 화면 col 나눈 후 ㅇㅇ -->

        <!-- <v-row>
          <v-col
            no-gutters
            fluid
            cols="8"
            class="border-style1"
            style="margin: 20px auto 0px auto"
          >
            <v-row
              no-gutters
              rows="9"
              fluid
              class="border-style1"
              style="margin: 5px"
            >
              <canvas
                class="border-style1"
                width="640"
                height="480"
                style="background-color: #619bed; margin: 20px 0px 0px 50px"
              ></canvas>
              <br />

              WebRTC 화면이 들어올 곳
            </v-row>
            <br />
            <v-row
              fluid
              no-gutters
              rows="2"
              class="border-style1"
              style="margin: 4px"
            >
              버튼 모음집
              <v-btn elevation="10" outlined tile rounded>
                <router-link to="/shot" style="margin: 10px"
                  >shot으로</router-link
                >

                <router-view />
              </v-btn>
              <v-btn class="pink white--text">
                <router-link to="/waiting" style="margin: 10px"
                  >waiting으로</router-link
                >
                |
              </v-btn>
            </v-row>
          </v-col>

          <v-col
            no-gutters
            fluid
            cols="3"
            class="border-style1"
            style="margin: 20px auto 0px auto"
          >
            배경 선택 할 carousel
            <v-card
              v-scroll.self="onScroll"
              class="overflow-y-auto"
              max-height="400"
            >
              <v-banner class="justify-center text-h5 font-weight-light" sticky>
                Scroll Me - Method invoked

                <span class="font-weight-bold" v-text="scrollInvoked"></span>

                times
              </v-banner>

              <v-card-text>
                <div v-for="n in 12" :key="n" class="mb-4">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Modi
                  commodi earum tenetur. Asperiores dolorem placeat ab nobis
                  iusto culpa, autem molestias molestiae quidem pariatur.
                  Debitis beatae expedita nam facere perspiciatis. Lorem ipsum
                  dolor sit amet consectetur adipisicing elit. Repellendus
                  ducimus cupiditate rerum officiis consequuntur laborum
                  doloremque quaerat ipsa voluptates, nobis nam quis nulla ullam
                  at corporis, similique ratione quasi illo!
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row> -->

        <!-- # 결과화면 들어가는곳  -->

        <v-row
          no-gutters
          rows="9"
          fluid
          class="border-style1"
          style="margin: 5px"
        >
          <v-container class="border-style1" style="margin: auto">
            <canvas
              ref="resultCanvas"
              class="border-style1"
              width="320"
              height="240"
              style="background-color: #b7f0b1; margin: 5px"
              v-if="page == 'result'"
            ></canvas>
            <div id="main-container" class="container" v-if="page == 'waiting'">
              <div id="session" v-if="session">
                <div id="session-header">
                  <h1 id="session-title">{{ room_no }}</h1>
                </div>

                <v-container>
                  <v-row>
                    <v-col>
                      <user-video
                        v-if="videoSetting"
                        :stream-manager="publisher"
                      />
                      <p v-show="readyStatus[user_name]" style="color: red">
                        ready
                      </p>
                    </v-col>
                    <v-col
                      v-for="sub in subscribers"
                      :key="sub.stream.connection.connectionId"
                    >
                      <user-video :stream-manager="sub" />
                      <p
                        v-show="
                          readyStatus[
                            JSON.parse(sub.stream.connection.data).clientData
                          ]
                        "
                        style="color: red"
                      >
                        ready
                      </p>
                    </v-col>
                  </v-row>
                </v-container>
              </div>
            </div>
          </v-container>
          WebRTC 화면이 들어올 곳
        </v-row>

        <v-row
          fluid
          no-gutters
          rows="2"
          class="border-style1"
          style="margin: 4px"
        >
          버튼 모음집
          <v-btn @click="cameraBtnHandler">
            {{ cameraBtnTxt }}
          </v-btn>
          <v-btn @click="micBtnHandler">
            {{ micBtnTxt }}
          </v-btn>
          <v-btn v-if="is_admin" v-bind:disabled="!allReady">start</v-btn>
          <v-btn v-if="!is_admin" @click="readyBtn">ready</v-btn>
          <v-btn elevation="9" outlined tile rounded>
            <button @click="savePhoto" style="margin: 10px">저장</button>
          </v-btn>
          <v-btn class="pink white--text">
            <button @click="sharePhoto" style="margin: 10px">공유</button>
          </v-btn>

          <v-btn id="buttonLeaveSession" @click="leaveBtn"> 나가기 </v-btn>
        </v-row>
        <!-- <br /> -->
      </v-col>
      <!-- <v-spacer></v-spacer> -->
      <!-- 오른쪽 영역 시작 -->
      <v-col
        fluid
        no-gutters
        cols="3"
        class="border-style1"
        style="margin: 10px auto 0px auto"
      >
        <!-- 참여 멤버 -->
        <v-row no-gutters fluid rows="4" class="border-style1">
          <!-- 이거 버튼 왜 안먹냐 오른쪽으로 붙는거-->
          <!-- 이거 링크 버튼임 -->
          <v-btn
            d-flex
            class="float-right"
            outlined
            rounded
            color="primary"
            @click="linkBtn"
          >
            링크
          </v-btn>
          <br />
          <layer-controller
            :layerSequence="layerSequence"
            :isAdmin="is_admin"
            :roomNo="room_no"
            @sendLayer="sendLayer"
          ></layer-controller>
        </v-row>
        <br />
        <!-- 채팅창 -->
        <v-row
          no-gutters
          fluid
          rows="6"
          class="border-style1"
          style="display: block; height: 350px"
        >
          <div
            style="word-break: break-all"
            v-for="(item, idx) in recvList"
            :key="idx"
          >
            <h4>{{ item.user_name }}: {{ item.chat_msg }}</h4>
          </div>
        </v-row>
        <!-- 채팅입력 -->
        <div class="form">
          <input
            class="form_input"
            type="text"
            placeholder="채팅을 입력하세요."
            v-model="message"
            @keyup.enter="sendMessage"
          />
          <div @click="sendMessage" class="form_submit">
            <svg
              width="30"
              height="30"
              viewBox="0 0 68 68"
              fill="#CCCCCC"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g clip-path="url(#clip0_26_10)">
                <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M48.0833 19.799C48.619 20.3347 48.806 21.127 48.5665 21.8457L35.8385 60.0294C35.5946 60.7614 34.9513 61.2877 34.1855 61.382C33.4198 61.4763 32.6681 61.1217 32.2539 60.4707L22.593 45.2893L7.41158 35.6285C6.76065 35.2142 6.40604 34.4625 6.50031 33.6968C6.59458 32.931 7.12092 32.2878 7.85287 32.0438L46.0366 19.3159C46.7553 19.0763 47.5476 19.2633 48.0833 19.799ZM26.5903 44.1204L33.3726 54.7782L42.0926 28.6181L26.5903 44.1204ZM39.2642 25.7897L23.7619 41.292L13.1041 34.5097L39.2642 25.7897Z"
                  fill=""
                />
              </g>
              <defs>
                <clipPath id="clip0_26_10">
                  <rect
                    width="48"
                    height="48"
                    fill="white"
                    transform="translate(33.9412) rotate(45)"
                  />
                </clipPath>
              </defs>
            </svg>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import Vue from "vue";
import Html2canvas from "html2canvas";
import Kakaosdk from "vue-kakao-sdk";
import { OpenVidu } from "openvidu-browser";
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";

import axios from "axios";

import stompApi from "@/api/stompApi.js";

import LayerController from "@/components/LayerController.vue";
import UserVideo from "@/components/UserVideo";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7a507.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
const apiKey = "59074e20c9d80e6e5200a4bd60122af7";
Vue.use(Kakaosdk, { apiKey });
export default {
  data() {
    return {
      user_name: "",
      message: "",
      room_no: "",
      users: [],
      recvList: [],

      myStatus: false,
      readyStatus: {},
      allReady: false,

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

      page: "waiting", // page가 waiting, shot, result로 변함에 따라 v-if로 교체.
    };
  },
  components: {
    UserVideo,
    LayerController,
  },
  computed: {
    inputVideo() {
      return this.$refs.input_video;
    },
    layerSequence() {
      return this.users.map((user) => user.user_name);
    },
  },
  created() {
    this.user_name = this.$route.params.user_name;
    this.room_no = this.$route.params.room_no + "";
    this.url = this.$route.params.url;
    this.is_admin = this.$route.params.is_admin;
  },
  mounted() {
    stompApi.connect(this.room_no, this.user_name, this.onSocketReceive);
    this.joinSession();
  },
  methods: {
    onSocketReceive(result) {
      const content = JSON.parse(result.body);
      console.log("socket received!");
      console.log(content);
      switch (content.action) {
        // 채팅방 입장 알림
        case 0:
          console.log("new user entered!!");
          this.recvList.push(content);
          this.users = content.users;
          this.readyJoin(content.users);
          break;
        // 채팅
        case 1:
          console.log(`${content.user_name} said ${content.chat_msg}`);
          this.recvList.push(content);
          break;
        // 준비
        case 2:
          this.ready(content);
          break;
        // 준비 취소
        case 3:
          this.ready(content);
          break;
        // 시작 하기
        case 4:
          break;
        // 레이어 변경하기
        case 5:
          console.log("layer changed!!");
          this.users = content.users;
          break;
        // 배경 선택하기
        case 6:
          break;
        // 촬영하기
        case 7:
          break;
        // 방장이 나감
        case 8:
          break;
        default:
          break;
      }
    },
    sendMessage() {
      if (this.user_name !== "" && this.message !== "") {
        console.log("Send message:" + this.message);
        if (stompApi.stomp && stompApi.stomp.connected) {
          stompApi.chat({
            user_name: this.user_name,
            chat_msg: this.message,
            room_no: this.room_no,
          });
        }
      }
      this.message = "";
    },
    sendLayer(userNames) {
      console.log("Send layer change:" + userNames);
      if (stompApi.stomp && stompApi.stomp.connected) {
        stompApi.layer({
          room_no: this.room_no,
          users: userNames.map((userName) =>
            this.users.find((user) => user.user_name == userName)
          ),
        });
      }
    },
    async savePhoto() {
      var date = new Date();
      var year = String(date.getFullYear());
      var yy = year.substring(2, 4);
      var month = new String(date.getMonth() + 1);
      var day = new String(date.getDate());

      if (month.length == 1) {
        month = "0" + month;
      }
      if (day.length == 1) {
        day = "0" + day;
      }

      var today = yy + month + day;

      console.log("저장중...");

      const el = this.$refs.resultCanvas;
      const options = {
        type: "dataURL",
      };
      const result = await Html2canvas(el, options);

      const link = document.createElement("a");
      link.setAttribute("download", "moweb_" + today + ".png");
      link.setAttribute(
        "href",
        result.toDataURL("image/png").replace("image/png", "image/octet-stream")
      );
      link.click();

      console.log("moweb_" + today + ".png 저장완료");
    },
    async sharePhoto() {
      console.log("공유하기");

      this.$kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: "모여봐요 웹캠으로",
          description:
            "<모여봐요 웹캠으로>를 통해 친구들과 재미있는 사진을 찍어보세요!",
          imageUrl: "https://i.ibb.co/88s0N4C/moweb.png",
          link: {
            mobileWebUrl: "https://i.ibb.co/88s0N4C/moweb.png",
            webUrl: "https://i.ibb.co/88s0N4C/moweb.png",
          },
        },
        buttons: [
          {
            title: "홈페이지 이동",
            link: {
              mobileWebUrl: "https://i7a507.p.ssafy.io/",
              webUrl: "https://i7a507.p.ssafy.io/",
            },
          },
        ],
      });
    },
    linkBtn() {
      if (!navigator.clipboard) {
        navigator.clipboard.writeText(this.url).then(() => {
          this.$dialog.message.success(this.url, {
            position: "top",
          });
        });
        return;
      }
      navigator.clipboard.writeText(this.url).then(() => {
        this.$dialog.message.success("url 복사 완료!", {
          position: "top",
        });
      });
    },
    //------------------------ready, start -------------------------
    readyBtn() {
      this.myStatus = !this.myStatus;
      if (stompApi.stomp && stompApi.stomp.connected) {
        stompApi.ready({
          user_name: this.user_name,
          room_no: this.room_no,
          status: this.myStatus,
        });
      }
    },
    readyJoin(users) {
      this.readyStatus = {};
      users.forEach((user) => {
        this.$set(this.readyStatus, user.user_name, user.status);
      });
      if (this.is_admin) {
        this.allReadyCheck();
      }
    },
    ready(data) {
      this.$set(this.readyStatus, data.user_name, data.status);
      if (this.is_admin) {
        this.allReadyCheck();
      }
    },
    allReadyCheck() {
      let size = 0;
      for (let user_name in this.readyStatus) {
        if (user_name == this.user_name) continue;
        size++;
        if (!this.readyStatus[user_name] || size < 1) {
          this.allReady = false;
          return;
        }
      }
      this.allReady = true;
    },
    // -------------------- webrtc start ------------------
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
    //----------------webrtc end----------------------
  },
};
</script>

<style scoped>
video {
  transform: rotateY(180deg);
}

.form {
  display: flex;
  justify-content: space-between;
  padding: 1.4rem;
  background: #ffffff;
  /* border-radius: 30px 30px 24px 24px; */
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
}

.form_input {
  border: none;
  padding: 0.5rem;
  font-size: 16px;
  width: calc(100% - 60px);
}

.form_input:focus {
  outline: none;
}

.form_submit {
  display: flex;
  align-items: center;
  cursor: pointer;
}

svg {
  transition: 0.3s;
}

svg:hover {
  fill: #999999;
}
</style>
