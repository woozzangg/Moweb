<template>
  <div class="maincon">
    <div class="leftcon">
      <div class="webrtc1">webrtc</div>
      <div class="btnzip">
        버튼모음
        <div id="app">
          <router-link to="/shot">shot으로</router-link>

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
  name: "WaitingView",
};
</script>

<style>
.maincon {
  background-color: rgb(204, 198, 198);
  width: 1500px;
  height: 700px;
  float: left;
  border: 2px solid rgb(0, 0, 0);
  padding: 3px;
}
.leftcon {
  background-color: rgb(213, 238, 168);
  width: 1040px;
  height: 690px;
  float: left;
  border: 2px solid rgb(0, 0, 0);
  padding: 3px;
}
.rightcon {
  background-color: rgb(118, 132, 216);
  width: 440px;
  height: 690px;
  float: right;
  border: 2px solid rgb(0, 0, 0);
  padding: 3px;
}
.webrtc1 {
  background-color: rgb(204, 157, 87);
  width: 1000px;
  height: 450px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  padding: 3px;
}
.btnzip {
  background-color: rgb(202, 81, 186);
  width: 1000px;
  height: 150px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  padding: 3px;
}
.members {
  background-color: rgb(3, 209, 54);
  width: 420px;
  height: 250px;
  float: top;
  border: 2px solid rgb(0, 0, 0);
  border-style: dotted;
  padding: 3px;
}
.chattt {
  background-color: rgb(57, 195, 230);
  width: 420px;
  height: 400px;
  float: bottom;
  border: 2px solid rgb(0, 0, 0);
  border-style: dashed;
  padding: 3px;
}
</style>
