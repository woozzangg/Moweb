<!--<template>
  <v-container
    class="border-style1"
    style="width: 1500px; height: 800px; margin: 100px"
  >
    <v-row
      class="ma-2"
      style="background-color: #000000"
      justify="space-between"
    >
      <v-col
        class="ma-4 border-style1"
        style="width: 69%; height: 97%; background-color: #0000ff"
      >
        <v-row class="ma-5 border-style1">
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #00ff00"
          ></canvas>
        </v-row>
        <v-row class="ma-2">
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000"
            ma-3
          ></canvas>
        </v-row>
      </v-col>
      <v-col
        class="ma-2 pa-0"
        style="width: 24%; height: 97%; background-color: #151515"
      >
        <v-row
          class="ma-2 pa-0 border-style1"
          style="width: 97%; height: 36%; background-color: rgb(35, 200, 49)"
          ><canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #00ff00"
          ></canvas
        ></v-row>
        <v-row
          class="ma-2 pa-0 border-style1"
          style="width: 97%; height: 60%; background-color: rgb(218, 63, 213)"
          >d</v-row
        >
      </v-col>
    </v-row>
  </v-container>
</template>-->
<template>
  <!-- <v-container
    fluid
    class="border-style1"
    style="width: 1600px; height: 750px; margin: 50px 150px 0px 160px"
  > -->
  <v-container class="border-style1">
    <v-row d-flex fluid justify-space-around style="margin: 0px">
      <v-col no-gutters fluid cols="8" class="border-style1">
        <!-- <v-col
        no-gutters
        fluid
        cols="8"
        class="border-style1"
        style="margin: 20px 0px 0px 35px"
      > -->
        <v-row
          no-gutters
          rows="9"
          fluid
          class="border-style1"
          style="margin: 5px"
        >
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #004400; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #f00; margin: 5px"
          ></canvas>
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          WebRTC 화면이 들어올 곳
        </v-row>
        <!-- <br /> -->
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
      <!-- <v-spacer></v-spacer> -->
      <v-col
        fluid
        no-gutters
        cols="3"
        class="border-style1"
        style="margin: 20px 0px 0px 50px"
      >
        <v-row no-gutters fluid rows="4" class="border-style1"
          >참여자 들어올 곳 a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />a<br />
        </v-row>
        <!-- <br /> -->
        <v-row no-gutters fluid rows="6" class="border-style1"
          >채팅창 들어올 곳</v-row
        >
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "ResultView",
};
</script>

<style>
.border-style1 {
  border: 1px solid rgb(0, 0, 0);
}
</style>
