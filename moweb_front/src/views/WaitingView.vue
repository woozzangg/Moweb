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
/* 메인페이지 */
/* .maincon { 
  background-color: rgb(204, 198, 198);
  width: 1600px;
  height: 700px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  margin: 90px 0px 0px 160px;
} */
.maincon {
  background-color: rgb(204, 198, 198);
  width: 1500px;
  height: 800px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  margin: 50px 0px 0px 220px;
}
/* 왼쪽 영역 */
/* .leftcon {
  background-color: rgb(213, 238, 168);
  width: 1150px;
  height: 690px;
  float: left;
  border: 2px solid rgb(0, 0, 0);
  margin: 3px;
} */
.leftcon {
  background-color: rgb(213, 238, 168);
  width: 1080px;
  height: 790px;
  float: left;
  border: 2px solid rgb(0, 0, 0);
  margin: 3px;
}
/* .rightcon {
  background-color: rgb(118, 132, 216);
  width: 430px;
  height: 690px;
  float: right;
  border: 2px solid rgb(0, 0, 0);
  margin: 3px;
} */

.rightcon {
  background-color: rgb(118, 132, 216);
  width: 400px;
  height: 790px;
  float: right;
  border: 2px solid rgb(0, 0, 0);
  margin: 3px;
}
/* .webrtc1 {
  background-color: rgb(204, 157, 87);
  width: 1130px;
  height: 500px;
  float: center;
  border: 2px solid rgb(0, 0, 0);

  margin: 5px;
} */
.webrtc1 {
  background-color: rgb(204, 157, 87);
  width: 1060px;
  height: 550px;
  float: center;
  border: 2px solid rgb(0, 0, 0);

  margin: 5px;
}
/* .btnzip {
  background-color: rgb(202, 81, 186);
  width: 1130px;
  height: 170px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  margin: 5px;
} */
.btnzip {
  background-color: rgb(202, 81, 186);
  width: 1060px;
  height: 220px;
  float: center;
  border: 2px solid rgb(0, 0, 0);
  margin: 5px;
}
/* .members {
  background-color: rgb(3, 209, 54);
  width: 410px;
  height: 250px;
  float: top;
  border: 2px solid rgb(0, 0, 0);
  border-style: dotted;
  margin: 5px;
} */
.members {
  background-color: rgb(3, 209, 54);
  width: 385px;
  height: 250px;
  float: top;
  border: 2px solid rgb(0, 0, 0);
  border-style: dotted;
  margin: 5px;
}
/* .chattt {
  background-color: rgb(57, 195, 230);
  width: 410px;
  height: 420px;
  float: bottom;
  border: 2px solid rgb(0, 0, 0);
  border-style: dashed;
  margin: 5px;
} */
.chattt {
  background-color: rgb(57, 195, 230);
  width: 385px;
  height: 520px;
  float: bottom;
  border: 2px solid rgb(0, 0, 0);
  border-style: dashed;
  margin: 5px;
}
</style>
