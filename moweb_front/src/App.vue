<template>
  <v-app id="app" style="height: 100%">
    <router-view />
  </v-app>
</template>
<script></script>

<style>
#app {
  background-color: beige;
}
/* font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50; */
/* height: 100vh;
  overflow: hidden;
  margin: 0; */
/* background-image: url("./assets/background.jpg"); */
/* background-size: cover;
  background-repeat: no-repeat;
  background-position: center; */
/* } */

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
.v-application {
  font-family: "SCDream", "NanumGgeu", sans-serif !important;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream1.otf);
  font-weight: 1;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream2.otf);
  font-weight: 2;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream3.otf);
  font-weight: 3;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream4.otf);
  font-weight: 4;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream5.otf);
  font-weight: 5;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream6.otf);
  font-weight: 6;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream7.otf);
  font-weight: 8;
}
@font-face {
  font-family: "SCDream";
  src: url(@/assets/fonts/SCDream9.otf);
  font-weight: 9;
}
@font-face {
  font-family: "NanumGgeu";
  src: url(@/assets/fonts/NanumGgeuTeuMeoRiCe.ttf);
}
</style>
