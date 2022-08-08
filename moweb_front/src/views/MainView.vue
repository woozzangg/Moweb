<template>
  <div class="background">
    <body>
      홈 화면
    </body>
  </div>
</template>

<script>
export default {
  name: "MainView",
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
