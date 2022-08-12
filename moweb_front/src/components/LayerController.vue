<template>
  <div class="layer_container">
    <h3 class="layer_title">참가자 목록</h3>
    <draggable
      class="layer_list"
      :list="dragLayerSequence"
      :move="checkMove"
      @end="onMove"
    >
      <transition-group>
        <div
          class="layer_item"
          v-for="(userName, index) in dragLayerSequence"
          :key="userName"
        >
          <div class="layer_no">
            {{ index + 1 }}
          </div>
          <div class="user_name">{{ userName }}</div>
        </div>
      </transition-group>
    </draggable>
  </div>
</template>

<script>
import draggable from "vuedraggable";

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

<style>
.layer_container {
  padding: 20px;
}
.layer_list {
}
.layer_item {
  padding: 4px;
  vertical-align: middle;
  border-radius: 4px;
}
.layer_item:hover {
  background-color: lightgray;
  cursor: pointer;
}
.layer_no {
  font-size: 1.2rem;
  box-sizing: border-box;
  display: inline-block;
  width: 24px;
  text-align: center;
}

.user_name {
  font-size: 1.2rem;
  display: inline-block;
}
</style>
