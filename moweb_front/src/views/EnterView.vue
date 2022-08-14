<template>
  <div>
    <v-container class="enter_head">
      <v-row>
        <v-col>
          <div>순간뽀짝</div>
          <div>모여봐요 웹캠으로</div>
        </v-col>
        <v-col align="center">
          <h1>입장하기</h1>
        </v-col>
        <v-col align="right">
          <h1>Moweb</h1>
        </v-col>
      </v-row>
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
              border-style: groove;
            "
            justify="center"
          ></canvas>
        </v-row>
        <v-row style="margin: 5px">
          <input
            placeholder="닉네임 입력"
            v-model="user_name"
            style="border-style: solid; margin: 20px auto 10px auto"
          />
        </v-row>
        <v-row fluid no-gutters rows="2" style="margin: 4px; padding: 0px">
          <v-btn
            v-if="!url"
            @click="createRoom"
            style="border-style: solid; margin: auto"
            >방만들기</v-btn
          >
          <v-btn
            v-if="url"
            @click="joinRoom"
            style="border-style: solid; margin: auto"
            >방입장하기</v-btn
          >
        </v-row>
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
    createRoom() {
      if (!this.alertDialog) return;
      if (!this.nameCheck()) return;
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
          }
        })
        .catch((err) => {
          console.log(err);
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
              .then(() => (this.alertDialog = true));
          } else if (data.room_no == 0) {
            this.$dialog
              .error({
                text: "이름이 중복되었습니다.",
                persistent: true,
              })
              .then(() => (this.alertDialogg = true));
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
          console.log(err);
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
.enter_body {
  width: 60%;
  margin: 0 auto;
  border: 15px solid white;
  border-radius: 15px;
  background-color: white;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.1);
}
.enter_head {
  width: 60%;
  margin: 20px auto;
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
</style>
