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
import { chromaKey } from "@/utils/chromakey.js";

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
    processV1() {
      // video-container에서 video 요소 직접 가져온 후
      // cavas에 그리고 초록배경 지우는 걸 반복해서 겹치는 함수
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
          this.canvasContext.putImageData(chromaKey(frame), 0, 0);
        }
      });
      requestAnimationFrame(this.processV1);
    },
    processV2() {
      // 기존에 크로마키 된 canvas를 elementid로 그냥 가져와서 그리기
      this.canvasContext.clearRect(0, 0, this.width, this.height);

      this.layerSequence.forEach((userName) => {
        const userCanvas = document.getElementById(`${userName}'s`);
        if (userCanvas) {
          this.canvasContext.drawImage(userCanvas, 0, 0);
        }
      });

      requestAnimationFrame(this.processV2);
    },
  },
};
</script>

<style></style>
