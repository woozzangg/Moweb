<template>
  <div>
    <h3>레이어 순서</h3>
    <draggable :list="dragLayerSequence" :move="checkMove" @end="onMove">
      <transition-group>
        <div v-for="(userName, index) in dragLayerSequence" :key="userName">
          {{ index + 1 }} | {{ userName }}
        </div>
      </transition-group>
    </draggable>
  </div>
</template>

<script>
import draggable from "vuedraggable";

import stompApi from "@/api/stompApi.js";

export default {
  // 레이어 순서, 방장 여부, prop으로 받음
  props: ["layerSequence", "isAdmin", "roomNo"],
  components: {
    draggable,
  },
  data: function () {
    return {
      dragLayerSequence: [],
    };
  },
  created() {
    this.dragLayerSequence = this.layerSequence.slice();
  },
  watch: {
    layerSequence() {
      this.dragLayerSequence = this.layerSequence.slice();
    },
  },
  methods: {
    checkMove() {
      // false 리턴하면 드래그 안됨
      return this.isAdmin;
    },
    onMove(event) {
      // 드래그 종료 이벤트
      // 이전 인덱스랑 새 인덱스가 다르면 layer 변경 websocket 호출
      if (event.newIndex !== event.oldIndex) {
        console.log("layer dragged!");
        this.$emit("sendLayer", this.dragLayerSequence);
      }
    },
  },
};
</script>

<style></style>
