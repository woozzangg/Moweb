<template>
  <div id="app">
    <!-- <nav> -->
    <router-link to="/">Home</router-link> |
    <router-link to="/enter">enter</router-link> |
    <router-link to="/waiting">waiting</router-link> |
    <router-link to="/shot">shot</router-link> |
    <router-link to="/result">result</router-link> |
    <router-link to="/webrtc">WebRTC</router-link>
    <!-- </nav> -->
    <router-view />
  </div>
</template>
<script></script>

<style>
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
