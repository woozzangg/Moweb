<template>
  <div style="margin: 10px 0px px 0px">
    <img src="@/assets/webcam.png" alt="" />

    <v-container class="border-style2" style="margin: 0px auto 0px auto">
      <v-col style="margin: 50px 0px 0px 0px">
        <v-row rows="1" no-gutters>
          <v-col class="text-center" md="6" offset-md="3" style="margin: auto">
            이름 들어갈 곳
          </v-col>
        </v-row>
        <v-row rows="6">
          <canvas
            width="640"
            height="480"
            style="background-color: #ff0000; margin: 0px auto 0px auto"
          ></canvas>
        </v-row>
        <v-row rows="1" class="centercss">
          <v-input>닉네임 입력창</v-input>
        </v-row>
        <v-row
          fluid
          no-gutters
          rows="2"
          class="border-style1"
          style="margin: 4px"
        >
          버튼 모음집
          <v-btn elevation="10" outlined tile rounded>
            <router-link to="/shot" style="margin: 10px">shot으로</router-link>

            <router-view />
          </v-btn>
          <v-btn class="pink white--text">
            <router-link to="/waiting" style="margin: 10px"
              >waiting으로</router-link
            >
            |
          </v-btn>
        </v-row>
      </v-col>
    </v-container>
  </div>
  <!-- 대안22222222222222 -->
  <!-- <v-container class="border-style1" d-flex justify-space-around>
    <v-col style="margin: 50px 0px 0px 0px">
      <v-row rows="1" no-gutters>
        <v-col md="6" offset-md="3"> 이름 들어갈 곳 </v-col>
      </v-row>
      <v-row rows="6" justify="center">
        <canvas
          mx="auto"
          class="border-style1"
          width="640"
          height="480"
          style="background-color: #ff0000; margin: 20px 0px 0px 50px"
        ></canvas>
      </v-row>
      <v-row rows="1" class="centercss">
        <v-input>닉네임 입력창</v-input>
      </v-row>
      <v-row
        fluid
        no-gutters
        rows="2"
        class="border-style1"
        style="margin: 4px"
      >
        버튼 모음집
        <v-btn elevation="10" outlined tile rounded>
          <router-link to="/shot" style="margin: 10px">shot으로</router-link>

          <router-view />
        </v-btn>
        <v-btn class="pink white--text">
          <router-link to="/waiting" style="margin: 10px"
            >waiting으로</router-link
          >
          |
        </v-btn>
      </v-row>
    </v-col>
  </v-container> -->
  <!-- 대안~~~~~~~~~~~~~~~~~~~~ -->
  <!-- <v-container class="border-style1" d-flex justify-space-around>
    <div style="margin: 50px 0px 0px 0px">
      <div class="mx-auto">이름 들어갈 곳</div>
      <div class="centercss">
        <canvas
          mx="auto"
          class="border-style1"
          width="640"
          height="480"
          style="background-color: #ff0000; margin: 20px 0px 0px 50px"
        ></canvas>
      </div>
      <div class="centercss">
        <v-input>닉네임 입력창</v-input>
      </div>
      <div>
        <v-row
          fluid
          no-gutters
          rows="2"
          class="border-style1"
          style="margin: 4px"
        >
          버튼 모음집
          <v-btn elevation="10" outlined tile rounded>
            <router-link to="/shot" style="margin: 10px">shot으로</router-link>

            <router-view />
          </v-btn>
          <v-btn class="pink white--text">
            <router-link to="/waiting" style="margin: 10px"
              >waiting으로</router-link
            >
            |
          </v-btn>
        </v-row>
      </div>
    </div>
  </v-container> -->
</template>

<script>
export default {
  name: "EnterView",
};
</script>

<style>
.centercss {
  float: center;
}

.border-style1 {
  border: 1px solid rgb(0, 0, 0);
}
.border-style2 {
  border: 25px solid rgb(0, 0, 0);
  border-radius: 15px;
}
img {
  display: block;
  margin: 0px auto;
}
</style>
