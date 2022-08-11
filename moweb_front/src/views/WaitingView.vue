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
        style="margin: 10px 10px 0px 10px"
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

        <!-- <v-row>
          <v-col
            no-gutters
            fluid
            cols="8"
            class="border-style1"
            style="margin: 20px 0px 0px 10px"
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
            style="margin: 20px 0px 0px 35px"
          >
            배경 선택 할 carousel
            <v-card
              v-scroll.self="onScroll"
              class="overflow-y-auto"
              max-height="400"
            >
              <v-banner class="justify-center text-h5 font-weight-light" sticky>
                Scroll Me - Method invoked

                <span class="font-weight-bold" v-text="scrollInvoked"></span>

                times
              </v-banner>

              <v-card-text>
                <div v-for="n in 12" :key="n" class="mb-4">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Modi
                  commodi earum tenetur. Asperiores dolorem placeat ab nobis
                  iusto culpa, autem molestias molestiae quidem pariatur.
                  Debitis beatae expedita nam facere perspiciatis. Lorem ipsum
                  dolor sit amet consectetur adipisicing elit. Repellendus
                  ducimus cupiditate rerum officiis consequuntur laborum
                  doloremque quaerat ipsa voluptates, nobis nam quis nulla ullam
                  at corporis, similique ratione quasi illo!
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row> -->

        <!-- # 결과화면 들어가는곳  -->
        <v-row
          no-gutters
          rows="9"
          fluid
          class="border-style1"
          style="margin: 5px"
        >
          <v-container
            class="border-style1"
            style="margin: 50px 100px 50px 100px"
          >
            <canvas
              ref="resultCanvas"
              class="border-style1"
              width="320"
              height="240"
              style="background-color: #b7f0b1; margin: 5px"
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
            <button @click="savePhoto" style="margin: 10px">저장</button>
          </v-btn>
          <v-btn class="pink white--text">
            <button @click="sharePhoto" style="margin: 10px">공유</button>
          </v-btn>
          <v-btn class="pink white--text">
            <router-link to="/" style="margin: 10px">나가기</router-link>
          </v-btn>
        </v-row>
        <!-- <br /> -->
      </v-col>
      <!-- <v-spacer></v-spacer> -->
      <!-- 오른쪽 영역 시작 -->
      <v-col
        fluid
        no-gutters
        cols="3"
        class="border-style1"
        style="margin: 10px 10px 0px 10px"
      >
        <!-- 참여 멤버 -->
        <v-row no-gutters fluid rows="4" class="border-style1">
          <!-- 이거 버튼 왜 안먹냐 오른쪽으로 붙는거-->
          <!-- 이거 링크 버튼임 -->
          <v-btn d-flex class="float-right" outlined rounded color="primary">
            Link
          </v-btn>
          <br />
          <layer-controller
            :layerSequence="layerSequence"
            :isAdmin="is_admin"
            :roomNo="room_no"
          ></layer-controller>
        </v-row>
        <br />
        <!-- 채팅창 -->
        <v-row no-gutters fluid rows="6" class="border-style1">
          <div v-for="(item, idx) in recvList" :key="idx">
            <h3>유저이름: {{ item.user_name }}</h3>
            <h3>내용: {{ item.chat_msg }}</h3>
          </div>
        </v-row>
        <input v-model="message" type="text" @keyup.enter="sendMessage" />
        <v-btn elevation="9" outlined tile rounded>
          <button @click="sendMessage" style="margin: 10px">입력</button>
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import Vue from "vue";
import Html2canvas from "html2canvas";
import Kakaosdk from "vue-kakao-sdk";

import stompApi from "@/api/stompApi.js";

import LayerController from "@/components/LayerController.vue";

const apiKey = "59074e20c9d80e6e5200a4bd60122af7";
Vue.use(Kakaosdk, { apiKey });

export default {
  components: {
    LayerController,
  },
  data() {
    return {
      users: [],
      user_name: "user1",
      is_admin: true,
      message: "",
      room_no: 1,
      recvList: [],
    };
  },
  computed: {
    layerSequence() {
      return this.users.map((user) => user.user_name);
    },
  },
  created() {
    this.user_name = "user1";
    this.room_no = "1";
  },
  mounted() {
    stompApi.connect(this.room_no, this.user_name, this.onSocketReceive);
    // this.connect();
  },
  methods: {
    onSocketReceive(result) {
      const content = JSON.parse(result.body);
      console.log("socket received!");
      console.log(content);
      switch (content.action) {
        // 채팅방 입장 알림
        case 0:
          console.log("new user entered!!");
          this.recvList.push(content);
          this.users = content.users;
          break;
        // 채팅
        case 1:
          console.log(`${content.user_name} said ${content.chat_msg}`);
          this.recvList.push(content);
          break;
        // 준비
        case 2:
          break;
        // 준비 취소
        case 3:
          break;
        // 시작 하기
        case 4:
          break;
        // 레이어 변경하기
        case 5:
          console.log("layer changed!!");
          this.users = content.users;
          break;
        // 배경 선택하기
        case 6:
          break;
        // 촬영하기
        case 7:
          break;
        // 방장이 나감
        case 8:
          break;
        default:
          break;
      }
    },
    sendMessage(e) {
      console.log("Send message:" + this.message);
      if (stompApi.stomp && stompApi.stomp.connected) {
        stompApi.chat({
          user_name: this.user_name,
          chat_msg: this.message,
          room_no: this.room_no,
        });
      }
      this.message = "";
    },
    changeSequence(userNames) {
      console.log("Send message:" + this.message);
      if (stompApi.stomp && stompApi.stomp.connected) {
        stompApi.layer({
          room_no: this.room_no,
          users: userNames.map((userName) =>
            this.users.find((user) => user.user_name == userName)
          ),
        });
      }
    },
    async savePhoto() {
      var date = new Date();
      var year = String(date.getFullYear());
      var yy = year.substring(2, 4);
      var month = new String(date.getMonth() + 1);
      var day = new String(date.getDate());

      if (month.length == 1) {
        month = "0" + month;
      }
      if (day.length == 1) {
        day = "0" + day;
      }

      var today = yy + month + day;

      console.log("저장중...");

      const el = this.$refs.resultCanvas;
      const options = {
        type: "dataURL",
      };
      const result = await Html2canvas(el, options);

      const link = document.createElement("a");
      link.setAttribute("download", "moweb_" + today + ".png");
      link.setAttribute(
        "href",
        result.toDataURL("image/png").replace("image/png", "image/octet-stream")
      );
      link.click();

      console.log("moweb_" + today + ".png 저장완료");
    },
    async sharePhoto() {
      console.log("공유하기");

      this.$kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: "모여봐요 웹캠으로",
          description:
            "<모여봐요 웹캠으로>를 통해 친구들과 재미있는 사진을 찍어보세요!",
          imageUrl: "https://i.ibb.co/88s0N4C/moweb.png",
          link: {
            mobileWebUrl: "https://i.ibb.co/88s0N4C/moweb.png",
            webUrl: "https://i.ibb.co/88s0N4C/moweb.png",
          },
        },
        buttons: [
          {
            title: "홈페이지 이동",
            link: {
              mobileWebUrl: "https://i7a507.p.ssafy.io/",
              webUrl: "https://i7a507.p.ssafy.io/",
            },
          },
        ],
      });
    },
  },
};
</script>
