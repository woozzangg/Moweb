<template>
  <div class="main-container">
    <help-modal></help-modal>
    <head>
      <title>모여봐요 웹캠으로</title>
    </head>
    <body>
      <div class="frame">
        <div class="photoBox">
          <div class="photoFourCut"></div>
        </div>
        <div class="titleForm">
          <span class="serviceTitle">모여봐요 웹캠으로</span
          ><span class="engTitle">Moweb</span>
          <span class="buttonForm">
            <router-link to="/enter" style="text-decoration: none">
              <div button class="startBtn">시작하기</div>
            </router-link>
          </span>
        </div>
        <div class="friends"></div>
        <span class="explainOne"
          >친구들과 사진을 촬영하고<br />잊지 못할 추억을 만들어 보세요!</span
        ><span class="explainTwo"
          >멀리 떨어져 있는 가족들과<br />
          병원에 입원해 있는 환자와<br />
          화상으로 미팅 중인 동료들과도<br />
          그때 그 순간을 기록할 수 있어요!</span
        >
        <div class="photoGroupTwo"></div>
        <div class="containerFirst"></div>
        <div class="pictureFirst"></div>
        <span class="stepFirst">STEP 1</span>
        <span class="usageFirst"
          >방을 생성한 후, 친구들에게<br />
          링크를 공유하여 초대하세요!
        </span>
        <div class="containerSecond"></div>
        <div class="pictureSecond"></div>
        <span class="stepSecond">STEP 2</span>
        <span class="usageSecond"
          >모든 준비가 끝나면,<br />
          카메라 버튼을 눌러 사진을 찍으세요!
        </span>
        <div class="containerThird"></div>
        <span class="stepThird">STEP 3</span>
        <div class="pictureThird"></div>
        <span class="usageThird"
          >촬영한 사진을 컴퓨터에 저장하고,<br />
          공유하기로 카카오톡에 자랑하세요!
        </span>
        <!-- <div class="containerLast"></div> -->
        <span class="titleLast">우리가 만들었어요</span>
        <div class="pictureLast"></div>
      </div>
      <div class="arrow" onclick="window.scrollTo(0,0);">
        <v-icon x-large color="white"> mdi-chevron-double-up </v-icon>
      </div>
    </body>
    <footer><h4>© 2022. Moweb Co. all rights reserved.</h4></footer>
  </div>
</template>
<script>
import HelpModal from "@/components/HelpModal.vue";

export default {
  name: "MainView",
  components: { HelpModal },
};
</script>

<style>
.main-container {
  min-width: 1300px;
  overflow-x: auto;
}
* {
  box-sizing: border-box;
}
body {
  font-size: 14px;
}
.frame {
  width: 100%;
  height: 4600px;
  background: #e8f0ff;
  opacity: 1;
  position: relative;
  top: 0px;
  left: 0px;
  overflow: hidden;
}
.arrow {
  cursor: pointer;
  position: fixed;
  top: 92%;
  right: 2%;
  border-radius: 50%;
  background-color: #30a4b0;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.2);
}
.arrow:hover {
  background-color: #008b99;
}

.photoFourCut {
  min-width: 330px;
  min-height: 395px;
  background: url("@/assets/images/photoFourCut.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 2%;
  left: 68%;
  overflow: hidden;
}
.titleForm {
  min-width: 777rem;
  height: 100%;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: relative;
  top: 2.5%;
  left: 20%;
  overflow: hidden;
}
.serviceTitle {
  min-width: 777rem;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 2.5%;
  left: 0px;
  font-family: SCDream;
  font-weight: 5;
  font-size: 4.5rem;
  opacity: 1;
  text-align: left;
}
.engTitle {
  width: 106rem;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 1.5%;
  left: 9px;
  font-family: NanumGgeu;
  font-size: 3rem;
  opacity: 1;
  text-align: left;
}

.startBtn {
  width: 230rem;
  height: 30rem;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 16rem;
  left: 14rem;
  overflow: hidden;
  text-align: center;
  color: white;
  background-color: #30a4b0;
  border-radius: 15px;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.2);
  line-height: 5rem;
  font-family: SCDream;
  font-weight: 6;
  letter-spacing: 2px;
  font-size: 25px;
  width: 10rem;
  height: 5rem;
}
.startBtn:hover {
  background-color: #008b99;
  font-size: 0;
  background-size: cover;
  background: url(@/assets/images/cameraStartColorSmall.png) center no-repeat;
}

.friends {
  width: 270px;
  height: 270px;
  background: url("@/assets/images/friends.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 85%;
  position: absolute;
  top: 16%;
  left: 16%;
  overflow: hidden;
}
.explainOne {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 18%;
  left: 58%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 2rem;
  opacity: 1;
  text-align: left;
}
.photoGroupTwo {
  width: 300px;
  height: 270px;
  background: url("@/assets/images/photoGroupTwo.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 28%;
  left: 58%;
  overflow: hidden;
}
.explainTwo {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 29%;
  left: 14.5%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 2rem;
  opacity: 1;
  text-align: left;
}
.containerFirst {
  width: 80%;
  height: 8.5%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 40%;
  left: 10%;
  border-radius: 15px;
  box-shadow: 0 2px 4px 0 rgba(14, 14, 14, 0.5);
  overflow: hidden;
}
.pictureFirst {
  width: 670px;
  height: 330px;
  background: url("@/assets/images/pictureFirst.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 40.8%;
  left: 12%;
  overflow: hidden;
}
.stepFirst {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 42.5%;
  left: 60%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.usageFirst {
  width: 450px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 43.5%;
  left: 60%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.containerSecond {
  width: 80%;
  height: 8.5%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 55%;
  left: 10%;
  border-radius: 15px;
  box-shadow: 0 2px 4px 0 rgba(14, 14, 14, 0.5);
  overflow: hidden;
}
.pictureSecond {
  width: 670px;
  height: 330px;
  background: url("@/assets/images/pictureSecond.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 55.8%;
  left: 12%;
  overflow: hidden;
}
.stepSecond {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 58%;
  left: 60%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.usageSecond {
  width: 460px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 59%;
  left: 60%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.containerThird {
  width: 80%;
  height: 8%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 70%;
  left: 10%;
  border-radius: 15px;
  box-shadow: 0 2px 4px 0 rgba(14, 14, 14, 0.5);
  overflow: hidden;
}
.pictureThird {
  width: 670px;
  height: 330px;
  background: url("@/assets/images/pictureThird.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 70.5%;
  left: 12%;
  overflow: hidden;
}
.stepThird {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 72.5%;
  left: 60%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.usageThird {
  width: 450px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 73.5%;
  left: 60%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.titleLast {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 86%;
  font-family: SCDream;
  font-weight: 6;
  font-size: 32px;
  opacity: 1;
  text-align: center;
}
.pictureLast {
  width: 1277px;
  height: 186px;
  background: url("@/assets/images/pictureLast.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  left: 10%;
  top: 88%;
  overflow: hidden;
}
</style>
