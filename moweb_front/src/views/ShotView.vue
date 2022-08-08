<template>
  <div class="maincon">
    <div class="leftcon">
      <div class="selecttheme">테마선택</div>
      <div class="webrtc2">webrtc</div>
      <div class="btnzip2">
        버튼모음
        <div id="app">
          <router-link to="/result">result 화면</router-link>

          <router-view />
        </div>
      </div>
    </div>
    <div class="rightcon">
      <div class="members">참여자</div>
      <div class="chattt">채팅창</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShotView",
};
</script>

<style scoped>
.selecttheme {
  background-color: rgb(214, 24, 24);
  width: 200px;
  height: 775px;
  float: right;
  border: 2px solid rgb(0, 0, 0);
  margin: 5px;
}
.webrtc2 {
  background-color: rgb(204, 157, 87);
  width: 860px;
  height: 550px;
  float: center;
  border: 2px solid rgb(0, 0, 0);

  margin: 5px;
}

.btnzip2 {
  background-color: rgb(202, 81, 186);
  width: 860px;
  height: 220px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  margin: 5px;
}
</style>
