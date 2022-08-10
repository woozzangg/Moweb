<template>
  <div class="background">
    <body>
      <v-btn>hi</v-btn>
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
  width: 1900px;
  height: 3000px;
  overflow: hidden;
  background-image: url("@/assets/mainpage.png");
  background-size: cover;
}
</style>
