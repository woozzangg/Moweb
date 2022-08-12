<template>
  <div>
    <head> </head>
    <body>
      <div class="frame">
        <div class="photoOne"></div>
        <div class="photoTwo"></div>
        <div class="titleForm">
          <span class="serviceTitle">모여봐요 웹캠으로</span
          ><span class="engTitle">Moweb</span>
          <span class="buttonForm">
            <router-link to="/enter" style="text-decoration: none"
              ><v-btn outlined x-large>시작하기</v-btn></router-link
            >
          </span>
        </div>
        <!-- <span class="logoTitle">순간뽀짝</span> -->
        <div class="photoGroupOne">
          <div class="cameraFrame"></div>
          <div class="friends"></div>
        </div>
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
      <!-- </div> -->
    </body>
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
.photoOne {
  width: 147px;
  height: 390px;
  background: url("@/assets/images/photoOne.jpg");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 3%;
  left: 68%;
  transform: rotate(-15deg);
  overflow: hidden;
}
.photoTwo {
  width: 147px;
  height: 390px;
  background: url("@/assets/images/photoTwo.jpg");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 2.5%;
  left: 74%;
  transform: rotate(11deg);
  overflow: hidden;
}
.titleForm {
  width: 777rem;
  height: 100%;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 3%;
  left: 20%;
  overflow: hidden;
}
.serviceTitle {
  width: 777rem;
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
/* .logoTitle {
  width: 94rem;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 0.3%;
  left: 89%;
  font-family: NanumGgeu;
  font-size: 2rem;
  opacity: 1;
  text-align: left;
} */
.buttonForm {
  width: 218rem;
  height: 30rem;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 17rem;
  left: 15rem;
  font-family: SCDream;
  font-weight: 4;
  font-size: 1rem;
  overflow: hidden;
}
.photoGroupOne {
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 27%;
  left: 16%;
  overflow: hidden;
}
.cameraFrame {
  width: 300px;
  height: 300px;
  background: url("@/assets/images/cameraFrame.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 100%;
  position: absolute;
  overflow: hidden;
}
.friends {
  width: 270px;
  height: 270px;
  background: url("@/assets/images/friends.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 85%;
  position: relative;
  top: 0;
  left: 2em;
  overflow: hidden;
}
.explainOne {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 30%;
  left: 58%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 2rem;
  opacity: 1;
  text-align: left;
}
.explainTwo {
  width: 100%;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 40%;
  left: 14.5%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 2rem;
  opacity: 1;
  text-align: left;
}
.photoGroupTwo {
  width: 270px;
  height: 270px;
  background: url("@/assets/images/photoGroupTwo.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 40%;
  left: 59%;
  overflow: hidden;
}
.containerFirst {
  width: 80%;
  height: 12%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 50%;
  left: 10%;
  overflow: hidden;
}
.pictureFirst {
  width: 640px;
  height: 360px;
  background: url("@/assets/images/pictureFirst.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 34.5%;
  left: 15%;
  overflow: hidden;
}
.stepFirst {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 35.5%;
  left: 62%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 33px;
  opacity: 1;
  text-align: left;
}
.usageFirst {
  width: 450px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 36.5%;
  left: 62%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 30px;
  opacity: 1;
  text-align: left;
}
.containerSecond {
  width: 80%;
  height: 12%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 65%;
  left: 10%;
  overflow: hidden;
}
.pictureSecond {
  width: 400px;
  height: 400px;
  background: url("@/assets/images/pictureSecond.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 45%;
  left: 21%;
  overflow: hidden;
}
.stepSecond {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 46.5%;
  left: 59%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 35px;
  opacity: 1;
  text-align: left;
}
.usageSecond {
  width: 450px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 47.5%;
  left: 59%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 30px;
  opacity: 1;
  text-align: left;
}
.containerThird {
  width: 80%;
  height: 8%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 72%;
  left: 10%;
  overflow: hidden;
}
.pictureThird {
  width: 640px;
  height: 360px;
  background: url("@/assets/images/pictureThird.png");
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  opacity: 1;
  position: absolute;
  top: 56.5%;
  left: 15%;
  overflow: hidden;
}
.stepThird {
  width: 130px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 58%;
  left: 62%;
  font-family: SCDream;
  font-weight: 5;
  font-size: 35px;
  opacity: 1;
  text-align: left;
}
.usageThird {
  width: 450px;
  color: rgba(0, 0, 0, 1);
  position: absolute;
  top: 59%;
  left: 62%;
  font-family: SCDream;
  font-weight: 3;
  font-size: 30px;
  opacity: 1;
  text-align: left;
}
.containerLast {
  width: 80%;
  height: 8%;
  background: rgba(236, 236, 236, 1);
  opacity: 1;
  position: absolute;
  top: 83%;
  left: 10%;
  overflow: hidden;
}
</style>
