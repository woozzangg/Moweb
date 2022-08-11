<template>
  <div>
    <h2>레이어 순서</h2>
    <draggable :list="dragLayerSequence" :move="checkMove" @end="onMove">
      <transition-group>
        <div v-for="(userName, index) in dragLayerSequence" :key="userName">
          {{ userName }} | {{ index + 1 }}
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
  props: ["layerSequence", "isKing", "roomNo"],
  components: {
    draggable,
  },
  data: function () {
    return {
      dragLayerSequence: [],
      tempList: [],
    };
  },
  watch: {
    layerSequence(val) {
      this.dragLayerSequence = val.slice();
    },
  },
  methods: {
    tempCallback(msg) {
      console.log(msg);
      this.tempList.push(msg);
    },
    checkMove() {
      // false 리턴하면 드래그 안됨
      return this.isKing;
    },
    onMove(event) {
      // 드래그 종료 이벤트
      // 이전 인덱스랑 새 인덱스가 다르면 layer 변경 websocket 호출
      if (event.newIndex !== event.oldIndex) {
        stompApi.chat(this.roomNo, this.dragLayerSequence);
      }
    },
  },
};
</script>

<style></style>
