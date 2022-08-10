<template>
  <div class="hello">
    <div id="call">
      <video ref="video" autoplay></video>
      <div>{{ msg }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WebrtcVideo",
  props: {
    msg: String,
  },
};
</script>

<style scoped></style>
