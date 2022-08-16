<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="1200">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          fab
          class="help-modal-button"
          color="white"
          dark
          v-bind="attrs"
          v-on="on"
        >
          <v-icon large color="#3A3C40">mdi-lightbulb-question-outline</v-icon>
        </v-btn>
      </template>
      <v-stepper v-model="helpStep" vertical>
        <!-- 1 단계 -->
        <v-stepper-step editable step="1" light-green lighten-3>
          방만들기 & 입장하기
        </v-stepper-step>
        <v-stepper-content step="1">
          <v-carousel v-model="model1" hide-delimiter-background :light="true">
            <v-carousel-item
              v-for="({ text, image }, index) in step1"
              :key="`step${index}`"
            >
              <v-sheet class="d-flex" color="grey lighten-3" height="100%">
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                  style="padding: 0 60px"
                >
                  <v-col align="center" cols="6">
                    <img :src="image" alt="" style="width: 100%" />
                  </v-col>
                  <v-col align="center" cols="6">
                    <p v-for="(msg, index) in text" :key="`step1-${index}`">
                      {{ msg }}
                    </p>
                  </v-col>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-stepper-content>

        <!-- 2단계 -->
        <v-stepper-step editable step="2"> 준비하기 & 시작하기 </v-stepper-step>
        <v-stepper-content step="2">
          <v-carousel v-model="model2" hide-delimiter-background :light="true">
            <v-carousel-item
              v-for="({ text, image }, index) in step2"
              :key="`step${index}`"
            >
              <v-sheet class="d-flex" color="grey lighten-3" height="100%">
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                  style="padding: 0 60px"
                >
                  <v-col align="center" cols="6">
                    <img :src="image" alt="" style="width: 100%" />
                  </v-col>
                  <v-col align="center" cols="6">
                    <p v-for="(msg, index) in text" :key="`step2-${index}`">
                      {{ msg }}
                    </p>
                  </v-col>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-stepper-content>

        <!-- 3단계 -->
        <v-stepper-step editable step="3"> 촬영하기 </v-stepper-step>
        <v-stepper-content step="3">
          <v-carousel v-model="model3" hide-delimiter-background :light="true">
            <v-carousel-item
              v-for="({ text, image }, index) in step3"
              :key="`step${index}`"
            >
              <v-sheet class="d-flex" color="grey lighten-3" height="100%">
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                  style="padding: 0 60px"
                >
                  <v-col align="center" cols="6">
                    <img :src="image" alt="" style="width: 100%" />
                  </v-col>
                  <v-col align="center" cols="6">
                    <p v-for="(msg, index) in text" :key="`step3-${index}`">
                      {{ msg }}
                    </p>
                  </v-col>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-stepper-content>

        <!-- 4단계 -->
        <v-stepper-step editable step="4"> 결과 확인 </v-stepper-step>
        <v-stepper-content step="4">
          <v-carousel v-model="model4" hide-delimiter-background :light="true">
            <v-carousel-item
              v-for="({ text, image }, index) in step4"
              :key="`step${index}`"
            >
              <v-sheet class="d-flex" color="grey lighten-3" height="100%">
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                  style="padding: 0 60px"
                >
                  <v-col align="center" cols="6">
                    <img :src="image" alt="" style="width: 100%" />
                  </v-col>
                  <v-col align="center" cols="6">
                    <p v-for="(msg, index) in text" :key="`step4-${index}`">
                      {{ msg }}
                    </p>
                  </v-col>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-stepper-content>
      </v-stepper>
    </v-dialog>
  </div>
</template>

<script>
export default {
  props: {
    page: String,
  },
  mounted() {
    if (this.page == "enter") {
      this.model1 = 1;
    } else if (this.page == "waiting") {
      this.helpStep = 2;
    }
  },
  watch: {
    page() {
      if (this.page == "shot") {
        this.helpStep = 3;
      } else if (this.page == "result") {
        this.helpStep = 4;
      }
    },
  },
  data: function () {
    return {
      dialog: false,
      helpStep: 1,
      model1: 0,
      model2: 0,
      model3: 0,
      model4: 0,
      step1: [
        {
          text: [
            "새 방을 만드시려면 '시작하기'를,",
            "초대를 받으셨다면 링크를 통해 접속해 주세요.",
          ],
          image: "/help/step1-1.png",
        },
        {
          text: [
            "캠 화면을 확인하고,",
            "사용할 이름을 입력해 주세요.",
            "'시작하기'를 누르셨으면 '방만들기'를,",
            "초대를 받으셨다면 '입장하기'를 눌러 주세요.",
            "(카메라가 작동하지 않는다면",
            "카메라 권한을 확인 후 허용해 주세요.)",
          ],
          image: "/help/step1-2.png",
        },
      ],
      step2: [
        {
          text: [
            "자, 이제 대기실에 입장하셨습니다.",
            "'초대' 버튼을 클릭하여 클립보드에 복사된",
            "링크를 공유하여 친구들을 초대하세요.",
          ],
          image: "/help/step1-3.png",
        },
        {
          text: [
            "참가자들의 캠 화면을 볼 수 있습니다.",
            "얼굴을 보며 참가자들과 대화를 나눠 보세요.",
          ],
          image: "/help/step2-1.png",
        },
        {
          text: [
            "화면 우측 상단의 참가자 목록에서",
            "방에 들어와 있는 사람들의",
            "닉네임을 확인할 수 있습니다.",
          ],
          image: "/help/step2-2.png",
        },
        {
          text: [
            "화면 우측 하단의 채팅 기능을 통해",
            "참가자들과 대화를 나눌 수 있습니다.",
            "채팅을 즐겨 보세요.",
          ],
          image: "/help/step2-3.png",
        },
        {
          text: [
            "하단의 '준비하기' 버튼을 이용하여",
            "상태 조작(READY)이 가능합니다.",
            "모든 참가자들이 준비를 완료하면",
            "'시작하기' 버튼을 눌러 촬영을 시작하세요.",
          ],
          image: "/help/step2-4.png",
        },
      ],
      step3: [
        {
          text: ["촬영을 시작하면", "촬영 화면을 확인할 수 있습니다."],
          image: "/help/step3-1.png",
        },
        {
          text: [
            "사진의 배경 색상을 선택할 수 있습니다.",
            "원하는 색을 골라 다양한 사진을 찍어 보세요.",
          ],
          image: "/help/step3-2.png",
        },
        {
          text: [
            "참가자 이름을 마우스로 드래그하여",
            "참가자들의 앞뒤 순서를 변경할 수 있습니다.",
            "친구들과 자유롭게 위치를 바꿔 보세요!",
          ],
          image: "/help/step3-3.png",
        },
        {
          text: [
            "촬영하기를 눌러",
            "총 4장의 사진을 찍어 주세요.",
            "카메라 버튼을 클릭하면",
            "5초의 타이머가 작동한 후 사진이 촬영됩니다.",
          ],
          image: "/help/step3-4.png",
        },
      ],
      step4: [
        {
          text: [
            "4장의 사진을 모두 찍으면,",
            "네 컷이 한 장으로 합쳐진",
            "결과 사진을 확인할 수 있습니다.",
          ],
          image: "/help/step4-1.png",
        },
        {
          text: ["찍은 사진을 다운로드하고,", "다른 친구들과 공유해 보세요."],
          image: "/help/step4-2.png",
        },
      ],
    };
  },
};
</script>

<style scoped>
p {
  font-size: 1.2rem;
}
.help-modal-button {
  position: fixed;
  top: 2%;
  right: 2%;
  z-index: 1;
}
.v-btn:hover:before {
  background-color: black;
}
</style>
