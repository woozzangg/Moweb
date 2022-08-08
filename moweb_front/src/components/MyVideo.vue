<template>
  <div>
    <video ref="video" autoplay id="video" />
    <canvas :width="width" :height="height" ref="myVideo" id="canvas" />
    <p>{{ myUserName }}</p>
  </div>
</template>

<script>
export default {
  name: "MyVideo",
  props: {
    myUserName: String,
    stream: MediaStream,
  },
  data() {
    return {
      ctx: undefined,
      videoElement: undefined,
      width: 320,
      height: 240,
    };
  },
  mounted() {
    this.init();
    // console.log(this.$refs["video"]);
    this.$refs["video"].srcObject = this.stream;
  },
  methods: {
    init() {
      let canvas = document.getElementById("canvas");
      this.videoElement = document.getElementById("video");
      this.ctx = canvas.getContext("2d");

      this.videoElement.addEventListener("canplay", this.chromaKey);
    },
    chromaKey() {
      this.ctx.clearRect(0, 0, this.width, this.height);
      this.ctx.scale(-1, 1);
      this.ctx.translate(-1 * this.width, 0);
      this.ctx.drawImage(this.videoElement, 0, 0, this.width, this.height);
      let frame = this.ctx.getImageData(0, 0, this.width, this.height);
      let l = frame.data.length / 4;

      // 녹색 배경 제거부분
      for (let i = 0; i < l; i++) {
        let r = frame.data[i * 4 + 0];
        let g = frame.data[i * 4 + 1];
        let b = frame.data[i * 4 + 2];
        if (g > 120 && r < 80 && b < 120) {
          // if (r == 0 && g == 158 && b == 43) { // 기준 색상
          frame.data[i * 4 + 3] = 0;
        }
      }
      this.ctx.putImageData(frame, 0, 0);
      this.ctx.scale(-1, 1);
      this.ctx.translate(-1 * this.width, 0);
      requestAnimationFrame(this.chromaKey);
    },
  },
};
</script>

<style></style>
