<template>
  <div>
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
          버튼을 눌러 촬영을 시작하세요!
        </span>
        <div class="containerThird"></div>
        <span class="stepThird">STEP 3</span>
        <div class="pictureThird"></div>
        <span class="usageThird"
          >촬영한 사진을 저장하고,<br />
          SNS에 자랑해 보세요!
        </span>
        <div class="containerLast"></div>
      </div>
      <div class="arrow" onclick="window.scrollTo(0,0);">
        <v-icon x-large color="white"> mdi-chevron-double-up </v-icon>
      </div>
    </body>
    <footer></footer>
  </div>
</template>
<script>
export default {
  name: "MainView",
};
</script>

<style>
* {
  box-sizing: border-box;
}
body {
  font-size: 14px;
}
.frame {
  width: 100%;
  height: 4500px;
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
  top: 3%;
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
  top: 3%;
  left: 20%;
  overflow: hidden;
}
.serviceTitle {
  min-width: 777rem;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 3%;
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
  top: 2%;
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
  top: 17rem;
  left: 15rem;
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
  top: 18%;
  left: 16%;
  overflow: hidden;
}
.explainOne {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 20%;
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
  top: 30%;
  left: 58%;
  overflow: hidden;
}
.explainTwo {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 31%;
  left: 14.5%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 2rem;
  opacity: 1;
  text-align: left;
}
.containerFirst {
  width: 80%;
  height: 10%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 43%;
  left: 10%;
  border-radius: 15px;
  box-shadow: 0 2px 4px 0 rgba(14, 14, 14, 0.5);
  overflow: hidden;
}
.pictureFirst {
  width: 560px;
  height: 315px;
  background: url("@/assets/images/pictureFirst.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 44.5%;
  left: 15%;
  overflow: hidden;
}
.stepFirst {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 46.5%;
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
  top: 47.5%;
  left: 60%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.containerSecond {
  width: 80%;
  height: 10%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 58%;
  left: 10%;
  border-radius: 15px;
  box-shadow: 0 2px 4px 0 rgba(14, 14, 14, 0.5);
  overflow: hidden;
}
.pictureSecond {
  width: 350px;
  height: 350px;
  background: url("@/assets/images/pictureSecond.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 59%;
  left: 21%;
  overflow: hidden;
}
.stepSecond {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 61.5%;
  left: 54%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.usageSecond {
  width: 450px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 62.5%;
  left: 54%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.containerThird {
  width: 80%;
  height: 10%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 73%;
  left: 10%;
  border-radius: 15px;
  box-shadow: 0 2px 4px 0 rgba(14, 14, 14, 0.5);
  overflow: hidden;
}
.pictureThird {
  width: 560px;
  height: 315px;
  background: url("@/assets/images/pictureThird.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 74.5%;
  left: 15%;
  overflow: hidden;
}
.stepThird {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 76.5%;
  left: 62%;
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
  top: 77.5%;
  left: 62%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 27px;
  opacity: 1;
  text-align: left;
}
.containerLast {
  width: 80%;
  height: 8%;
  background: rgb(255, 179, 179);
  opacity: 1;
  position: absolute;
  top: 88%;
  left: 10%;
  overflow: hidden;
}
</style>
