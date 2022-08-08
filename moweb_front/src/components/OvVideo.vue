<template>
  <div>
    <video ref="input_video" />
    <video autoplay :ref="client" :id="client" v-show="false" />
    <canvas :width="width" :height="height" :id="canvasId" />
    <!-- <div>
      <p>{{ client }}</p>
    </div> -->
  </div>
</template>

<script>
// import { Camera } from "@mediapipe/camera_utils";
import { SelfieSegmentation } from "@mediapipe/selfie_segmentation";

export default {
  name: "OvVideo",

  data() {
    return {
      ctx: undefined,
      videoElement: undefined,
      width: 320,
      height: 240,
      canvasId: this.client + "'s",
    };
  },
  props: {
    streamManager: Object,
    client: String,
  },
  computed: {
    inputVideo() {
      return this.$refs.input_video;
    },
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      console.log(this.client, this.streamManager);
      //video에 추가
      this.streamManager.addVideoElement(this.$refs[this.client]);
      // this.$refs[this.client].srcObject = this.streamManager.stream.mediaStream;
      let canvas = document.getElementById(this.canvasId);
      this.videoElement = document.getElementById(this.client);
      this.ctx = canvas.getContext("2d");

      this.videoElement.addEventListener("canplay", this.removeBG);
    },

    // 배경 색 제거
    async removeBG() {
      this.ctx = document.getElementById(this.canvasId).getContext("2d");
      const selfieSegmentation = new SelfieSegmentation({
        locateFile: (file) => {
          return `https://cdn.jsdelivr.net/npm/@mediapipe/selfie_segmentation/${file}`;
        },
      });
      selfieSegmentation.setOptions({
        modelSelection: 1,
      });
      selfieSegmentation.onResults(this.onResults);
      const selfieSend = () => {
        setTimeout(async () => {
          // console.log(this);
          await selfieSegmentation.send({ image: this.videoElement });
          selfieSend();
        }, 1000 / 30);
      };
      selfieSend();
      // this.camera = new Camera(this.inputVideo, {
      //   onFrame: async () => {
      //     console.log(this.inputVideo);
      //     await selfieSegmentation.send({ image: this.inputVideo });
      //   },
      //   width: 320,
      //   height: 240,
      // });
      // this.camera.start();
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
      // this.ctx.globalCompositeOperation = "source-out";
      // this.ctx.fillStyle = "#009933"; // 배경색 부분
      // this.ctx.fillRect(0, 0, results.image.width, results.image.height);

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
