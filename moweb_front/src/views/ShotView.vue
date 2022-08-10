<template>
  <v-container
    fluid
    class="border-style1"
    style="width: 1600px; height: 750px; margin: 50px 150px 0px 160px"
  >
    <v-row d-flex fluid justify-space-around style="margin: 0px">
      <v-col
        no-gutters
        fluid
        cols="6"
        class="border-style1"
        style="margin: 20px 0px 0px 35px"
      >
        <v-row
          no-gutters
          rows="9"
          fluid
          class="border-style1"
          style="margin: 5px"
        >
          <canvas
            class="border-style1"
            width="640"
            height="480"
            style="background-color: #ff0000; margin: 20px 0px 0px 50px"
          ></canvas>
          <br />

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
      <v-col
        no-gutters
        fluid
        cols="2"
        class="border-style1"
        style="margin: 20px 0px 0px 35px"
      >
        배경 선택 할 carousel
        <v-carousel v-model="model">
          <v-carousel-item v-for="(color, i) in colors" :key="color">
            <v-sheet :color="color" height="50%" width="50%" tile>
              <v-col class="fill-height" align="center" justify="center">
                <div class="text-h2">Slide {{ i + 1 }}</div>
              </v-col>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
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
  name: "ShotView",
};
</script>
