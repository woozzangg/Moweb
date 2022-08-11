<template>
  <!-- 컨테이너 시작 -->
  <v-container class="border-style1" style="margin: 20px 0px 0px 50px">
    <!-- row로 구간 나눔 -->
    <v-row d-flex fluid justify-space-around style="margin: 0px">
      <!-- 왼쪽 영역 -->
      <v-col
        no-gutters
        fluid
        cols="8"
        class="border-style1"
        style="margin: 10px auto 0px auto"
      >
        <!-- //////////// 여기서 v-if나 v-show로  if else 걸어서 두기 -->
        <!-- ///////// 정상화면 row 9 , 2 -->
        <!-- # WebRTC 화면 부분 -->
        <!-- <v-row
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
            style="background-color: #00ff00; margin: 5px"
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
          <canvas
            class="border-style1"
            width="320"
            height="240"
            style="background-color: #ff0000; margin: 5px"
          ></canvas>
          WebRTC 화면이 들어올 곳
        </v-row>

        <v-row
          fluid
          no-gutters
          rows="2"
          class="border-style1"
          style="margin: 4px"
        >
          버튼 모음집
          <v-btn elevation="9" outlined tile rounded>
            <router-link to="/shot" style="margin: 10px">shot으로</router-link>

            <router-view />
          </v-btn>
          <v-btn class="pink white--text">
            <router-link to="/waiting" style="margin: 10px"
              >waiting으로</router-link
            >
            |
          </v-btn>
        </v-row> -->

        <!-- 촬영화면 들어가는곳 -->
        <!-- # 배경선택 들어갔을때 화면 col 나눈 후 ㅇㅇ -->

        <v-row>
          <v-col
            no-gutters
            fluid
            cols="8"
            class="border-style1"
            style="margin: 20px auto 0px auto"
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
                style="background-color: #619bed; margin: 20px 0px 0px 50px"
              ></canvas>
              <br />

              WebRTC 화면이 들어올 곳
            </v-row>
            <br />
            <v-row
              fluid
              no-gutters
              rows="2"
              class="border-style1"
              style="margin: 4px"
            >
              버튼 모음집
              <v-btn elevation="10" outlined tile rounded>
                <router-link to="/shot" style="margin: 10px"
                  >shot으로</router-link
                >

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
            cols="3"
            class="border-style1"
            style="margin: 20px auto 0px auto"
          >
            <!-- 카르셀 일탄 포기 - 스크롤 해봄 -->
            <!-- <v-carousel
              height="400px"
              v-model="model"
              vertical="true"
              hide-delimiters
            >
              <v-carousel-item v-for="(color, i) in colors" :key="color">
                <v-sheet :color="color" height="80%" width="50%">
                  <v-col
                    class="fill-height"
                    align="center"
                    justify="center"
                    style="margin: auto"
                  >
                    <div class="text-h2">Slide {{ i + 1 }}</div>
                  </v-col>
                </v-sheet>
              </v-carousel-item>
            </v-carousel> -->
            <!-- 카드로 스크롤 형태 ㅇ -->
            <v-card class="flex-grow-0" fluid style="margin: auto">
              <v-virtual-scroll
                :items="Array.from({ length: 100 }).map((_, index) => index)"
                :item-height="70"
                height="600px"
              >
                <template v-slot="{ item }">
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>Item {{ item }}</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-virtual-scroll>
            </v-card>
          </v-col>
        </v-row>

        <!-- # 결과화면 들어가는곳  -->

        <!-- <v-row
          no-gutters
          rows="9"
          fluid
          class="border-style1"
          style="margin: 5px"
        >
          <v-container
            class="border-style1; aaaaaa"
            style="margin: 50px 100px 50px 100px"
          >
            <canvas
              class="border-style1"
              width="320"
              height="240"
              style="background-color: #00ff00; margin: 5px"
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
          </v-container>
          WebRTC 화면이 들어올 곳
        </v-row>

        <v-row
          fluid
          no-gutters
          rows="2"
          class="border-style1"
          style="margin: 4px"
        >
          버튼 모음집
          <v-btn elevation="9" outlined tile rounded>
            <router-link to="/shot" style="margin: 10px">shot으로</router-link>

            <router-view />
          </v-btn>
          <v-btn class="pink white--text">
            <router-link to="/waiting" style="margin: 10px"
              >waiting으로</router-link
            >
            |
          </v-btn>
        </v-row> -->
        <!-- <br /> -->
      </v-col>
      <!-- <v-spacer></v-spacer> -->
      <!-- 오른쪽 영역 시작 -->
      <v-col
        fluid
        no-gutters
        cols="3"
        class="border-style1"
        style="margin: 10px auto 0px auto"
      >
        <!-- 참여 멤버 -->
        <v-row no-gutters fluid rows="4" class="border-style1">
          <!-- 이거 버튼 왜 안먹냐 오른쪽으로 붙는거-->
          <!-- 이거 링크 버튼임 -->
          <v-btn d-flex class="float-right" outlined rounded color="primary">
            Link
          </v-btn>
          <br />
          참여자 들어올 곳
        </v-row>
        <br />
        <!-- 채팅창 -->
        <v-row no-gutters fluid rows="6" class="border-style1"
          >채팅창 들어올 곳 <br />
          <!-- <v-virtual-scroll height="100" item-height="20">

          /></v-virtual-scroll> -->
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "WaitingView",

  // data: () => ({
  //   model: 0,
  //   colors: ["primary", "secondary", "yellow darken-2", "red", "orange"],
  // }),
  data: () => ({
    scrollInvoked: 0,
  }),

  methods: {
    onScroll() {
      this.scrollInvoked++;
    },
  },
};
</script>
