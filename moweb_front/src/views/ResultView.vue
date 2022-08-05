<template>
  <div class="maincon">
    <div class="leftcon">
      <div class="webrtc1">webrtc</div>
      <div class="btnzip">
        버튼모음
        <div id="app">
          <router-link to="/shot">다시찍기 shot으로</router-link>

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
  name: "ResultView",
};
</script>

<style>
.maincon {
  background-color: rgb(204, 198, 198);
  width: 1500px;
  height: 700px;
  float: left;
  border: 1px solid rgb(0, 0, 0);
  float: left;
  padding: 1px;
}
.leftcon {
  background-color: rgb(213, 238, 168);
  width: 900px;
  height: 700px;
  float: left;
  border: 1px solid rgb(0, 0, 0);
  padding: 1px;
}
.rightcon {
  background-color: rgb(118, 132, 216);
  width: 400px;
  height: 700px;
  float: right;
  border: 1px solid rgb(0, 0, 0);
  padding: 1px;
}
.webrtc1 {
  background-color: rgb(204, 157, 87);
  width: 900px;
  height: 450px;
  float: center;
  border: 1px solid rgb(0, 0, 0);
  padding: 1px;
}
.btnzip {
  background-color: rgb(202, 81, 186);
  width: 900px;
  height: 150px;
  float: center;
  border: 1px solid rgb(0, 0, 0);
  padding: 1px;
}
.members {
  background-color: rgb(3, 209, 54);
  width: 350px;
  height: 250px;
  float: top;
  border: 1px solid rgb(0, 0, 0);
  padding: 1px;
}
.chattt {
  background-color: rgb(57, 195, 230);
  width: 350px;
  height: 400px;
  float: bottom;
  border: 1px solid rgb(0, 0, 0);
  padding: 1px;
}
</style>
