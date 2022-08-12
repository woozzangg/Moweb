<template>
  <v-container class="border-style1" d-flex justify-space-around>
    <video v-show="false" ref="input_video"></video>
    <v-col cols="10">
      <v-row>
        <canvas
          class="output_canvas"
          id="output_canvas"
          :width="width"
          :height="height"
          style="transform: rotateY(180deg); margin: auto"
        ></canvas>
      </v-row>
      <v-row class="centercss">
        <input
          placeholder="닉네임 입력"
          v-model="user_name"
          style="border-style: solid"
        />
      </v-row>
      <v-row
        fluid
        no-gutters
        rows="2"
        class="border-style1"
        style="margin: 4px"
      >
        버튼 모음집
        <v-btn v-if="!url" @click="createRoom">방만들기</v-btn>
        <v-btn v-if="url" @click="joinRoom">방입장하기</v-btn>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";
import axios from "axios";

//const ROOT_URL = "https://i7a507.p.ssafy.io";
//const API_URL = "https://i7a507.p.ssafy.io/moweb-api";
const ROOT_URL = "http://localhost:8081";
const API_URL = "http://localhost:8080/moweb-api";

export default {
  name: "EnterView",
  data() {
    return {
      width: 720,
      height: 540,
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
      axios
        .post(
          API_URL + "/room/join",
          JSON.stringify({
            user_name: this.user_name,
            url: this.url,
          })
        )
        .then(({ data }) => {
          if (data.room_no == -2) {
            alert("들어갈수 없는 방입니다.");
            this.$router.replace({ name: "main" });
          } else if (data.room_no == -1) {
            alert("방 인원이 가득찼습니다.");
          } else if (data.room_no == 0) {
            alert("이름이 중복되었습니다.");
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
  },
};
</script>

<style scoped>
.centercss {
  float: center;
}
</style>
