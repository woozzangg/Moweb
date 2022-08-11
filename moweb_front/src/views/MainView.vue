<template>
  <div class="background">
    <body>
      <router-link to="/enter" style="text-decoration: none"
        ><v-btn>시작하기</v-btn></router-link
      >
    </body>
  </div>
</template>

<script>
export default {
  name: "MainView",
};
</script>

<style scoped>
.mint {
  background-color: #12d3a9;
}
.choco {
  background-color: #563d34;
}
.background {
  width: 1900px;
  height: 3000px;
  overflow: hidden;
  background-image: url("@/assets/mainpage.png");
  background-size: cover;
}
</style>
