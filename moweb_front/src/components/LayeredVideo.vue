<template>
  <div>
    <canvas
      :width="width"
      :height="height"
      id="layered-output-canvas"
      ref="layeredOutputCanvas"
    ></canvas>
  </div>
</template>

<script>
export default {
  props: ["width", "height", "layerSequence", "mapUserVideo"],
  data: function () {
    return {
      canvasContext: null,
    };
  },
  computed: {},
  mounted() {
    this.canvasContext = this.$refs.layeredOutputCanvas.getContext("2d");
    this.processV2();
  },
  methods: {
    process() {
      this.canvasContext.clearRect(0, 0, this.width, this.height);

      this.layerSequence.forEach((userName) => {
        const videoElement = document.getElementById(
          this.mapUserVideo[userName]
        );

        if (videoElement) {
          // 맨 뒤에 붙이기
          this.canvasContext.globalCompositeOperation = "destination-over";
          this.canvasContext.drawImage(
            videoElement,
            0,
            0,
            this.width,
            this.height
          );

          // 초록색 제거해서 그리기
          this.canvasContext.globalCompositeOperation = "copy";
          let frame = this.canvasContext.getImageData(
            0,
            0,
            this.width,
            this.height
          );
          this.canvasContext.putImageData(this.chromaKey(frame), 0, 0);
        }
      });
      requestAnimationFrame(this.process);
    },
    processV2() {
      this.canvasContext.clearRect(0, 0, this.width, this.height);

      this.layerSequence.forEach((userName) => {
        const userCanvas = document.getElementById(`${userName}'s`);
        if (userCanvas) {
          this.canvasContext.drawImage(userCanvas, 0, 0);
        }
      });

      requestAnimationFrame(this.processV2);
    },
    chromaKey: function (frame) {
      let l = frame.data.length / 4;

      for (let i = 0; i < l; i++) {
        let r = frame.data[i * 4 + 0];
        let g = frame.data[i * 4 + 1];
        let b = frame.data[i * 4 + 2];
        if (g > 120 && r < 80 && b < 120) {
          // if (r == 0 && g == 158 && b == 43) { // 기준 색상
          frame.data[i * 4 + 3] = 0;
        }
      }

      return frame;
    },
  },
};
</script>

<style></style>
