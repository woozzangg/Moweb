<template>
  <!-- 컨테이너 시작 -->
  <v-container class="app-container" style="padding: 0 0 30px">
    <help-modal></help-modal>
    <v-container class="app_head">
      <v-row>
        <v-col align="center">
          <h1>Moweb</h1>
        </v-col>
      </v-row>
    </v-container>
    <v-container class="app_body" style="overflow: hidden">
      <!-- row로 구간 나눔 -->
      <!-- 캠에서 가져온 소스비디오 -->
      <video v-show="false" ref="input_video"></video>
      <canvas
        v-show="false"
        id="personal_canvas"
        width="960"
        height="720"
        ref="personal_canvas"
      >
      </canvas>
      <!-- webrtc를 통해 보낼 소스 -->
      <canvas
        v-show="false"
        class="output_canvas"
        id="output_canvas"
        :width="width"
        :height="height"
      ></canvas>
      <v-row d-flex fluid justify-space-around style="margin: 0px">
        <!-- 왼쪽 영역 -->
        <div
          no-gutters
          style="width: 71%; margin: 5px auto 0px auto; min-height: 751px"
        >
          <v-container class="webrtc" style="min-height: 85%; margin: 0px">
            <div
              style="
                margin: 20px auto;
                width: 100%;
                height: 100%;
                display: flex;
              "
              v-if="page == 'result'"
            >
              <table
                ref="resultCanvas"
                style="margin: auto; background: black; align-items: center"
              >
                <th></th>
                <th></th>
                <tr>
                  <td style="padding: 12px 12px 0 12px">
                    <img :src="resultImg[0]" width="320" height="240" />
                  </td>
                  <td style="padding: 12px 12px 0 0">
                    <img :src="resultImg[1]" width="320" height="240" />
                  </td>
                </tr>
                <tr>
                  <td style="padding: 12px 12px 0 12px">
                    <img :src="resultImg[2]" width="320" height="240" />
                  </td>
                  <td style="padding: 12px 12px 0 0">
                    <img :src="resultImg[3]" width="320" height="240" />
                  </td>
                </tr>
                <tr>
                  <td
                    style="padding: 12px 12px; text-align: left; color: white"
                  >
                    <span>모여봐요 웹캠으로</span>
                    <span
                      style="text-align: left; padding: 0 4px; font-size: 10px"
                    >
                      by 순간뽀짝
                    </span>
                  </td>
                  <td
                    style="
                      vertical-align: middle;
                      padding: 12px 12px;
                      text-align: right;
                      color: white;
                    "
                  >
                    {{
                      new Date()
                        .toISOString()
                        .split("T")[0]
                        .replaceAll("-", ".")
                    }}
                  </td>
                </tr>
              </table>
            </div>
            <div
              id="main-container"
              class="container"
              v-if="session && page !== 'result'"
            >
              <div id="session">
                <v-container>
                  <v-row style="justify-content: center">
                    <v-col style="flex-grow: 0">
                      <user-video
                        :style="waitingStyle"
                        v-if="videoSetting"
                        :stream-manager="publisher"
                      />
                      <p
                        v-if="page == 'waiting'"
                        v-show="readyStatus[user_name]"
                        style="color: red; position: absolute"
                      >
                        Ready
                      </p>
                    </v-col>
                    <v-col
                      style="flex-grow: 0"
                      v-show="page == 'waiting'"
                      v-for="sub in subscribers"
                      :key="sub.stream.connection.connectionId"
                    >
                      <user-video :stream-manager="sub" />
                      <p
                        v-if="
                          readyStatus[
                            JSON.parse(sub.stream.connection.data).clientData
                          ]
                        "
                        style="color: red; position: absolute"
                      >
                        Ready
                      </p>
                    </v-col>
                  </v-row>
                </v-container>
                <v-container v-if="page == 'shot'">
                  <v-col>
                    <v-row class="justify-space-around">
                      <layered-video
                        width="640"
                        height="480"
                        :backgroundCode="backGroundImg"
                        :layerSequence="layerSequence"
                        :shotCnt="shot_cnt"
                        ref="layeredVideo"
                      ></layered-video>
                    </v-row>
                    <v-row class="justify-space-around">
                      <v-dialog
                        transition="dialog-top-transition"
                        max-width="320"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn color="primary" v-bind="attrs" v-on="on"
                            >배경 색상 선택</v-btn
                          >
                        </template>
                        <template v-slot:default="dialog">
                          <v-card min-height="370">
                            <v-color-picker
                              v-model="bg_code"
                              dot-size="12"
                              hide-mode-switch
                              mode="hexa"
                              style="margin: auto; padding-top: 10px"
                            ></v-color-picker>
                            <v-card-actions class="justify-end">
                              <v-btn text @click="backGroundBtn"
                                >적용하기</v-btn
                              >
                              <v-btn text @click="dialog.value = false"
                                >닫기</v-btn
                              >
                            </v-card-actions>
                          </v-card>
                        </template>
                      </v-dialog>
                    </v-row>
                  </v-col>
                </v-container>
              </div>
            </div>
          </v-container>

          <v-container
            no-gutters
            class="btnzip"
            style="height: 8%; margin: 4px; padding:2px; justify-content-center"
          >
            <v-row style="margin: auto">
              <v-col>
                <v-btn @click="cameraBtnHandler" v-if="page != 'result'" large>
                  <v-icon v-if="cameraOn" x-large>mdi-video</v-icon>
                  <v-icon v-if="!cameraOn" x-large>mdi-video-off</v-icon>
                </v-btn>
                <v-btn @click="micBtnHandler" large>
                  <v-icon v-if="micOn" x-large>mdi-microphone</v-icon>
                  <v-icon v-if="!micOn" x-large>mdi-microphone-off</v-icon>
                </v-btn>
              </v-col>
              <div class="main_btn">
                <v-btn
                  color="blue"
                  dark
                  large
                  v-if="is_admin && page == 'waiting'"
                  v-bind:disabled="!allReady"
                  @click="startBtn"
                  style="width: 100%"
                >
                  start
                </v-btn>
                <v-btn
                  large
                  color="primary"
                  v-if="!is_admin && page == 'waiting'"
                  @click="readyBtn"
                  style="width: 100%"
                >
                  ready
                </v-btn>
                <div
                  class="save_btn"
                  v-if="page == 'result'"
                  @click="savePhoto"
                >
                  저장
                </div>
                <div
                  class="share_btn"
                  v-if="page == 'result'"
                  @click="sharePhoto"
                >
                  공유
                </div>
                <!-- 촬영화면 다이얼로그  start -->
                <shot-modal
                  v-if="page === 'shot'"
                  :dialogProp="shotDialog"
                  :count="count"
                  :isAdmin="is_admin"
                  @sendShotCountdown="sendShotCountdown"
                  @sendDialogChange="sendDialogChange"
                >
                  <layered-video
                    width="960"
                    height="720"
                    :backgroundCode="backGroundImg"
                    :layerSequence="layerSequence"
                    :shotCnt="shot_cnt"
                  >
                  </layered-video>
                </shot-modal>
              </div>
              <v-col align="right">
                <exit-modal
                  :is_admin="is_admin"
                  @leaveSession="leaveSession"
                  style="float: right; margin: auto"
                >
                </exit-modal>
              </v-col>
            </v-row>
          </v-container>
        </div>
        <!-- 오른쪽 영역 시작 -->
        <div style="width: 26%; margin: 5px auto px auto">
          <!-- 참여 멤버 -->
          <div no-gutters class="members" style="height: 33%; margin: auto">
            <!-- 이거 링크 버튼임 -->
            <v-btn
              v-if="page == 'waiting'"
              d-flex
              class="linkbtn"
              rounded
              color="primary"
              @click="linkBtn"
              position="absolute"
            >
              링크
            </v-btn>
            <layer-controller
              :layerSequence="layerSequence"
              :isAdmin="is_admin"
              :roomNo="room_no"
              @sendLayer="sendLayer"
              :page="page"
            ></layer-controller>
          </div>
          <br />
          <!-- 채팅창 -->
          <div no-gutters class="chat_body" v-chat-scroll>
            <div
              class="chat_message"
              v-for="(chat, idx) in chatList"
              :key="idx"
            >
              <h4>{{ chat }}</h4>
            </div>
          </div>
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
                fill="#757575"
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
        </div>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import Vue from "vue";

import { OpenVidu } from "openvidu-browser";
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";

import axios from "axios";

import stompApi from "@/api/stompApi.js";

import LayerController from "@/components/LayerController.vue";
import UserVideo from "@/components/UserVideo";
import LayeredVideo from "@/components/LayeredVideo.vue";
import ShotModal from "@/components/ShotModal.vue";
import ExitModal from "@/components/ExitModal.vue";
import HelpModal from "@/components/HelpModal.vue";

import VueChatScroll from "vue-chat-scroll";

import Html2canvas from "html2canvas";
import Kakaosdk from "vue-kakao-sdk";

import shutterSoundSource from "@/assets/sounds/camera_click_sound.wav";
import beepSoundSource from "@/assets/sounds/beep_sound.wav";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7a507.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
const API_URL = "https://i7a507.p.ssafy.io/moweb-api";
const apiKey = "59074e20c9d80e6e5200a4bd60122af7";
Vue.use(Kakaosdk, { apiKey });

Vue.use(VueChatScroll);

export default {
  data() {
    return {
      benched: 0,
      user_name: "",
      message: "",
      room_no: "",
      users: [],
      chatList: [],

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

      backGroundImg: "#3D939EFF",
      bg_code: "",
      waitingStyle: "",
      page: "waiting", // page가 waiting, shot, result로 변함에 따라 v-if로 교체.
      count: 0,
      shotDialog: false,
      shot_cnt: 0,
      resultImg: [],
      shutterSound: new Audio(shutterSoundSource),
      countdownSound: new Audio(beepSoundSource),
    };
  },
  components: {
    UserVideo,
    LayerController,
    LayeredVideo,
    ShotModal,
    ExitModal,
    HelpModal,
  },
  computed: {
    inputVideo() {
      return this.$refs.input_video;
    },
    layerSequence() {
      return this.users.map((user) => user.user_name);
    },
    // 배경선택부분
    items() {
      return Array.from({ length: this.length }, (_k, v) => v + 1);
    },
    length() {
      return 20;
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
    this.picturectx = document
      .getElementById("personal_canvas")
      .getContext("2d");
    this.picturectx.scale(-1, 1);
    this.picturectx.translate(-960, 0);
    if (this.room_no == "undefined") {
      this.$router.replace("/");
    }
  },
  methods: {
    async onSocketReceive(result) {
      const content = JSON.parse(result.body);
      switch (content.action) {
        // 채팅방 입장 알림
        case 0:
          this.chatList.push("[알림] " + content.chat_msg);
          this.users = content.users;
          this.readyJoin(content.users);
          break;
        // 채팅
        case 1:
          this.chatList.push(content.user_name + ": " + content.chat_msg);
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
          this.mowebStart();
          break;
        // 레이어 변경하기
        case 5:
          this.users = content.users;
          break;
        // 배경 선택하기
        case 6:
          this.backGroundImg = content.bg_code;
          break;
        // 촬영하기
        case 7:
          this.shot_cnt = content.shot_cnt;
          await this.takepic();
          if (this.shot_cnt === 4) {
            this.page2Result();
          }
          break;
        // 방장이 나감
        case 8:
          console.log("BOOM!");
          alert("호스트가 방을 종료했습니다.", this.leaveSession());
          break;
        // 촬영화면 다이얼로그 토글
        case 9:
          this.shotDialog = content.status;
          break;
        // 카운트다운 시작
        case 10:
          this.startShotCount();
          break;
        default:
          break;
      }
    },
    backGroundBtn() {
      stompApi.theme({
        room_no: this.room_no,
        bg_code: this.bg_code,
      });
    },
    // ---------------------------- 화면 전환 start --------------------------------
    mowebStart() {
      this.page = "shot";
      this.waitingStyle = "visibility:hidden; position:absolute";
    },
    sendMessage() {
      if (this.user_name !== "" && this.message !== "") {
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

    async page2Result() {
      this.page = "result";
      this.camera.stop();
      this.shotDialog = false;
      for (let i = 1; i <= 4; i++) {
        await axios
          .get(
            API_URL +
              "/display?imgName=canvas_img_" +
              this.room_no +
              "_" +
              i +
              ".png"
          )
          .then(async (res) => {
            this.resultImg.push(res.request.responseURL);
          });
      }
    },

    // ----------------------------- 화면 전환 end --------------------------------
    sendLayer(userNames) {
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
      let date = new Date();

      let year = String(date.getFullYear());
      year = year.substring(2, 4);
      let month = date.getMonth() + 1;
      month = month >= 10 ? month : "0" + month;
      let day = date.getDate();
      day = day >= 10 ? day : "0" + day;
      let hour = date.getHours();
      hour = hour >= 10 ? hour : "0" + hour;
      let min = date.getMinutes();
      let sec = date.getSeconds();
      sec = sec >= 10 ? sec : "0" + sec;

      let timestamp = year + month + day + hour + min + sec;

      const el = this.$refs.resultCanvas;
      const options = {
        type: "dataURL",
        useCORS: true,
        proxy: "html2canvasproxy.php",
        logging: true,
      };
      const result = await Html2canvas(el, options);

      const link = document.createElement("a");
      link.setAttribute("download", "moweb_" + timestamp + ".png");
      link.setAttribute(
        "href",
        result.toDataURL("image/png").replace("image/png", "image/octet-stream")
      );
      link.click();
    },
    async sharePhoto() {
      this.uploadResult();

      const fileName = "canvas_img_" + this.room_no + "_result.png";
      const fileUrl = API_URL + "/display?imgName=" + fileName;

      this.$kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: "모여봐요 웹캠으로",
          description:
            "<모여봐요 웹캠으로>를 통해 친구들과 재미있는 사진을 찍어보세요!",
          imageUrl: fileUrl,
          link: {
            mobileWebUrl: fileUrl,
            webUrl: fileUrl,
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
    async uploadResult() {
      const resultCanvas = this.$refs.resultCanvas;
      Html2canvas(resultCanvas, {
        useCORS: true,
        proxy: "html2canvasproxy.php",
        logging: true,
      }).then((canvas) => {
        let image = canvas.toDataURL("image/png");
        let name = "canvas_img_" + this.room_no + "_result.png";

        let byteString = atob(image.split(",")[1]);
        let ab = new ArrayBuffer(byteString.length);
        let ia = new Uint8Array(ab);

        for (let i = 0; i < byteString.length; i++) {
          ia[i] = byteString.charCodeAt(i);
        }
        let blob = new Blob([ab], { type: "image/png" });

        let formData = new FormData();
        formData.append("image", blob, name);

        axios.post(API_URL + "/upload", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
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
    startBtn() {
      stompApi.start({
        room_no: this.room_no,
      });
    },
    // ---------------------------- ready end ------------------
    // -------------------- shot start -------------------
    startShotCount() {
      this.count = 5000;
      this.shotTick();
    },
    shotTick() {
      this.countdownSound.pause();
      this.countdownSound.currentTime = 0;
      this.countdownSound.play();
      setTimeout(() => {
        this.count -= 1000;
        if (!this.shotDialog) {
          // 촬영화면 닫으면 카운트 중단
          this.countdownSound.pause();
          this.countdownSound.currentTime = 0;
          this.count = 0;
        } else if (this.count > 0) {
          // 카운트
          this.shotTick();
        } else {
          // do something shot here
          this.shutterSound.play();
          if (this.is_admin) {
            stompApi.shot({
              room_no: this.room_no,
              shot_cnt: this.shot_cnt + 1,
              bg_code: this.bg_code,
            });
          }
        }
      }, 1000);
    },
    sendShotCountdown() {
      stompApi.shotCountdown({
        room_no: this.room_no,
      });
    },
    sendDialogChange(dialog) {
      // 여기서 동기화를 위해 다이얼로그 전송
      stompApi.shotMode({
        room_no: this.room_no,
        status: dialog,
      });
    },
    // -------------------- shot end -------------------
    // -------------------- webrtc start ------------------
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      this.OV.enableProdMode();

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
            console.error(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
      window.addEventListener("popstate", this.leaveSession);
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
      stompApi.disconnect();
      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.replace("/").then(() => {
        window.location.reload();
      });
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
        width: 960,
        height: 720,
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
    //------------------------------------ 자신의 사진 누끼------------
    async pictureBackground() {
      if (!this.cameraOn) {
        this.picturectx.clearRect(0, 0, 960, 720);
        return;
      }

      const selfieSegmentation = new SelfieSegmentation({
        locateFile: (file) => {
          return `https://cdn.jsdelivr.net/npm/@mediapipe/selfie_segmentation/${file}`;
        },
      });
      selfieSegmentation.setOptions({
        modelSelection: 1,
      });
      selfieSegmentation.onResults(this.resultOption);

      await selfieSegmentation.send({ image: this.inputVideo });

      return 1;
    },

    // 배경제거 옵션
    async resultOption(results) {
      this.picturectx.save();
      this.picturectx.clearRect(0, 0, 960, 720);
      this.picturectx.drawImage(results.segmentationMask, 0, 0, 960, 720);

      // Only overwrite missing pixels.
      this.picturectx.globalCompositeOperation = "source-in";
      await this.picturectx.drawImage(results.image, 0, 0, 960, 720);

      this.picturectx.restore();
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
    //--------------사진찍기 -----------------------
    async takepic() {
      // await this.pictureBackground(); // 각자의 사진으로 할때
      // let canvas = this.$refs["personal_canvas"]; // 각자의 사진으로 할때
      if (!this.is_admin) return; // 공유화면에서 사진찍을 때
      const canvas = this.$refs.layeredVideo.$refs.layeredOutputCanvas;
      const imgBase64 = canvas.toDataURL("image/png");
      const decodImg = atob(imgBase64.split(",")[1]);

      let array = [];
      for (let i = 0; i < decodImg.length; i++) {
        array.push(decodImg.charCodeAt(i));
      }
      const file = new Blob([new Uint8Array(array)], { type: "image/png" });
      const fileName =
        "canvas_img_" + this.room_no + "_" + this.shot_cnt + ".png";
      let formData = new FormData();
      formData.append("image", file, fileName);

      await axios.post(API_URL + "/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
    },
  },
};
</script>

<style>
.app-container {
  min-width: 1300px;
  overflow-x: auto;
}
.app_head {
  margin-top: -12px;
  font-family: NanumGgeu;
  font-size: 2.4rem;
}
.app_body {
  min-width: 1200px;
  margin: 0 auto;
  padding: 0px;
  border: 15px solid white;
  border-radius: 15px;
  background-color: white;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.1);
}

video {
  transform: rotateY(180deg);
}

.chat_body {
  height: 400px;
  padding: 0.4rem;
  margin: auto;
  overflow-y: auto;
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
  background-color: #f0f2f5;
  border-radius: 15px 15px 0px 0px;
  padding: 1.4rem;
}

.chat_message {
  margin-top: 10px;
  margin-bottom: 10px;
  word-wrap: break-word;
}

.form {
  display: flex;
  justify-content: space-between;
  padding: 1.4rem;
  background: #f0f2f5;
  border-radius: 0px 0px 15px 15px;
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
  fill: #3c3c3c;
}

.linkbtn {
  float: right;
  margin: 3px 3px;
}
.members {
  background-color: #faf4aa;
  border-radius: 15px;

  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
}
.webrtc {
  background-color: #fff0f0;
  border-radius: 15px;
  padding: 2px;

  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
}
.btnzip {
  width: 99%;
  position: relative;
}
.btncss {
  position: absolute;
  left: 35%;

  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
}
.main_btn {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 30%;
}

.save_btn {
  padding: 0.4rem 1rem 0.4rem 1rem;
  display: flex;
  height: 44px;
  width: 40%;
  font-size: 16px;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  background: #f5f5f5;
  border-radius: 4px;
  box-shadow: 0px 3px 1px -2px rgb(0 0 0 / 20%),
    0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%);
}

.save_btn:hover {
  background: #dbdbdb;
}

.share_btn {
  padding: 0.4rem 1rem 0.4rem 1rem;
  display: flex;
  height: 44px;
  width: 40%;
  font-size: 16px;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: white;
  background: #30a4b0;
  border-radius: 4px;
  box-shadow: 0px 3px 1px -2px rgb(0 0 0 / 20%),
    0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%);
}

.share_btn:hover {
  background: #008b99;
}
</style>
