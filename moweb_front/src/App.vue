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

<style>
/* #app { */
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
</style>
