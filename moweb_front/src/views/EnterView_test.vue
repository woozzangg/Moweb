<template>
  <v-container>
    <video v-show="false" ref="input_video"></video>
    <v-col>
      <v-row justify="center">
        <canvas
          class="output_canvas"
          id="output_canvas"
          :width="width"
          :height="height"
          style="transform: rotateY(180deg)"
        ></canvas>
      </v-row>
      <v-row>
        <input
          placeholder="닉네임 입력"
          v-model="user_name"
          style="border-style: solid"
        />
        <input
          placeholder="url, join테스트"
          v-model="url"
          style="border-style: solid"
        />
      </v-row>
      <v-row>
        <v-btn @click="createRoom">방만들기</v-btn>
        <v-btn @click="joinRoom">방참가하기</v-btn>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";
import axios from "axios";

const API_URL = "https://i7a507.p.ssafy.io/moweb-api";

axios.defaults.headers.post["Content-Type"] = "application/json";
export default {
  name: "EnterView",
  data() {
    return {
      width: 0,
      height: 0,
      user_name: "",
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
      axios
        .post(
          API_URL + "/room/create",
          JSON.stringify({
            user_name: this.user_name,
          })
        )
        .then(({ data }) => {
          if (data.room_no > 0) {
            this.$router.push({
              name: "webrtc",
              params: {
                user_name: this.user_name,
                room_no: data.room_no,
                url: data.url,
              },
            });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    joinRoom() {
      axios
        .post(
          API_URL + "/room/join",
          JSON.stringify({
            user_name: this.user_name,
            url: this.url,
          })
        )
        .then(({ data }) => {
          console.log(data.room_no);
          if (data.room_no > 0) {
            this.camera.stop();
            this.$router.push({
              name: "webrtc",
              params: {
                user_name: this.user_name,
                room_no: data.room_no,
                url: data.url,
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
  },
};
</script>

<style scoped>
.mint {
  background-color: #12d3a9;
}
.choco {
  background-color: #563d34;
}
.background {
  height: 500vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/mainpage.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>
