<template>
  <div>
    <video
      autoplay
      :ref="client"
      :id="client"
      style="visibility: hidden; position: absolute"
    />
    <canvas
      :width="width"
      :height="height"
      :id="canvasId"
      :style="talkingCss"
    />
  </div>
</template>

<script>
export default {
  name: "OvVideo",

  data() {
    return {
      ctx: undefined,
      videoElement: undefined,
      width: 320,
      height: 240,
      canvasId: this.client + "'s",
      talkingCss:
        "posiotion: absolute; border-color: black; border-style: groove; border-radius:5px; box-shadow: 4px 4px 4px #0000008c",
    };
  },
  props: {
    streamManager: Object,
    client: String,
  },

  mounted() {
    this.init();
  },
  methods: {
    async init() {
      //video에 추가
      await this.streamManager.addVideoElement(this.$refs[this.client]);
      this.streamManager.on("publisherStartSpeaking", () => {
        this.isTalking(true);
      });
      this.streamManager.on("publisherStopSpeaking", () => {
        this.isTalking(false);
      });
      let canvas = document.getElementById(this.canvasId);
      this.videoElement = document.getElementById(this.client);
      this.ctx = canvas.getContext("2d");

      this.videoElement.addEventListener("canplay", this.chromaKey);
    },

    // 배경 색 제거
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
    isTalking(boolean) {
      if (boolean) {
        this.talkingCss =
          "posiotion: absolute; border-color: aqua; border-style: groove; border-radius:5px; box-shadow: 4px 4px 4px #0000008c";
      } else {
        this.talkingCss =
          "posiotion: absolute; border-color: black; border-style: groove; border-radius:5px; box-shadow: 4px 4px 4px #0000008c";
      }
    },
  },
};
</script>
