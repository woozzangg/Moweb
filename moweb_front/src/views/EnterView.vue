<template>
  <div class="enter-container">
    <v-container class="enter-head">
      <v-row>
        <v-col align="center">
          <h1>Moweb</h1>
        </v-col>
      </v-row>
      <div class="polaroid"></div>
    </v-container>
    <v-container class="enter_body" d-flex justify-space-around>
      <video v-show="false" ref="input_video"></video>
      <v-col cols="12">
        <v-row>
          <canvas
            class="output_canvas"
            id="output_canvas"
            :width="width"
            :height="height"
            style="
              transform: rotateY(180deg);
              margin: auto;
              <!-- border: 2px solid #000000; -->
            "
            justify="center"
          ></canvas>
        </v-row>
        <div class="nickname">
          <input
            class="nickname_input"
            placeholder="닉네임 입력"
            v-model="user_name"
          />
          <div
            v-if="!url"
            class="nickname_submit"
            id="createRoomBtn"
            @click="createRoom"
          >
            방만들기
          </div>
          <div v-if="url" class="nickname_submit" @click="joinRoom">
            방입장하기
          </div>
        </div>
      </v-col>
    </v-container>
  </div>
</template>

<script>
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";
import axios from "axios";

const ROOT_URL = "https://i7a507.p.ssafy.io";
const API_URL = "https://i7a507.p.ssafy.io/moweb-api";

export default {
  name: "EnterView",
  data() {
    return {
      width: 720,
      height: 540,
      user_name: "",
      alertDialog: true,
      url: "",
    };
  },
  computed: {
    inputVideo() {
      return this.$refs.input_video;
    },
  },
  mounted() {
    this.removeBG();
    this.url = this.$route.params.url;
  },
  methods: {
    btnOn() {
      const btn = document.getElementById("createRoomBtn");
      btn.disabled = false;
    },
    async btnOff() {
      const btn = document.getElementById("createRoomBtn");
      btn.disabled = true;
    },
    async createRoom() {
      if (!this.alertDialog) return;
      if (!this.nameCheck()) return;
      await this.btnOff();
      axios
        .post(
          API_URL + "/room/create",
          JSON.stringify({
            user_name: this.user_name,
          })
        )
        .then(({ data }) => {
          this.camera.stop();
          if (data.room_no > 0) {
            this.$router.replace({
              name: "waiting",
              params: {
                is_admin: true,
                user_name: this.user_name,
                room_no: data.room_no,
                url: ROOT_URL + data.url,
              },
            });
          } else {
            this.btnOn();
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    joinRoom() {
      if (!this.alertDialog) return;
      if (!this.nameCheck()) return;
      axios
        .post(
          API_URL + "/room/join",
          JSON.stringify({
            user_name: this.user_name,
            url: this.url,
          })
        )
        .then(({ data }) => {
          this.alertDialog = false;
          if (data.room_no == -2) {
            this.$dialog.error({
              text: "들어갈수 없는 방입니다.",
              persistent: true,
            });
            this.$router.replace({ name: "main" });
          } else if (data.room_no == -1) {
            this.$dialog
              .error({
                text: "방 인원이 가득찼습니다.",
                persistent: true,
              })
              .then(() => {
                this.alertDialog = true;
                this.btnOn();
              });
          } else if (data.room_no == 0) {
            this.$dialog
              .error({
                text: "이름이 중복되었습니다.",
                persistent: true,
              })
              .then(() => {
                console.log(this);
                this.alertDialog = true;
                this.btnOn();
              });
          }
          if (data.room_no > 0) {
            this.camera.stop();
            this.$router.replace({
              name: "waiting",
              params: {
                is_admin: false,
                user_name: this.user_name,
                room_no: data.room_no,
                url: ROOT_URL + this.url,
              },
            });
          }
        })
        .catch((err) => {
          console.error(err);
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
        width: 720,
        height: 540,
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

      // Only overwrite missing pixels.
      this.ctx.globalCompositeOperation = "source-in";
      await this.ctx.drawImage(
        results.image,
        0,
        0,
        results.image.width,
        results.image.height
      );

      this.ctx.restore();
    },
    nameCheck() {
      let flag = false;
      if (this.user_name.length > 0 && this.user_name.length < 11) flag = true;
      if (!flag) {
        this.alertDialog = false;
        this.$dialog
          .error({
            text: "닉네임은 1자이상 10자이하만 가능합니다.",
            persistent: true,
          })
          .then(() => (this.alertDialog = true));
      }
      return flag;
    },
  },
};
</script>

<style>
.enter-container {
  min-width: 1000px;
  min-height: 1000px;
  overflow-x: auto;
}
.enter-head {
  margin: 0 auto;
  padding-top: 5px;
  font-family: NanumGgeu;
  font-size: 3rem;
}
/* .polaroid {
  background: url("@/assets/images/favicon.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  overflow: hidden;
} */
.enter_body {
  width: fit-content;
  margin: 10px auto;
  border-radius: 15px;
  padding: 30px 50px;
  background-color: white;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.1);
}
.border-style1 {
  border: 1px solid rgb(0, 0, 0);
}
.border-style2 {
  border: 15px solid rgb(46, 5, 5);
  border-radius: 15px;
  background-color: white;
}
img {
  display: block;
  margin: 0px auto;
}
.nickname {
  display: flex;
  justify-content: center;
  margin-top: 60px;
  padding: 0.2rem;
}

.nickname_input {
  padding: 0.7rem;
  font-size: 22px;
  text-align: center;
  align-items: center;
  width: calc(80% - 60px);
  background: #f0f2f5;
  border-radius: 15px 0px 0px 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.05);
}

.nickname_input:focus {
  outline: none;
}

.nickname_submit {
  padding: 0.4rem 1.5rem 0.4rem 1.5rem;
  font-size: 22px;
  letter-spacing: 1px;
  display: flex;
  align-items: center;
  cursor: pointer;
  color: white;
  background: #30a4b0;
  border-radius: 0px 15px 15px 0px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.05);
}
</style>
