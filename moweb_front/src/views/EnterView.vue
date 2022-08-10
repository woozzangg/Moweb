<template>
  <v-container class="border-style1" d-flex justify-space-around>
    <v-col cols="10">
      <div style="margin: 50px 0px 0px 0px">
        <div class="centercss">
          <canvas
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
        </div>
      </div>
    </v-col>
  </v-container>
</template>

<script>
export default {
  name: "EnterView",
};
</script>

<style scoped>
.centercss {
  float: center;
}
</style>
