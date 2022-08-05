<template>
  <v-container class="grey lighten-5">
    <v-row rows="12">
      <!-- no-gutters -->
      <v-col cols="9">
        <v-card class="pa-2" outlined tile height="60vh"> 컴포 </v-card>
      </v-col>
      <v-col cols="3">
        <v-row class="pa-2" rows="6" cols="3">
          <v-card class="ma-2" outlined tile height="25vh" weight="10vh">
            참여자
          </v-card>
        </v-row>
        <v-row rows="7">
          <v-card class="pa-0" outlined tile height="35vh"> 채팅창</v-card>
        </v-row>
        <!-- <v-card class="pa-2" outlined tile height="70vh">
          <v-row rows="9">123</v-row>
          <v-row rows="3">11</v-row>
        </v-card> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "WaitingView",
};
</script>

<style>
.mint {
  background-color: #12d3a9;
}
.choco {
  background-color: #563d34;
}
.background {
  height: 500vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/mainpage.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>
