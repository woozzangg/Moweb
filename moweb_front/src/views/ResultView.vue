<template>
  <div class="maincon">
    <div class="leftcon">
      <div class="webrtc1">webrtc</div>
      <div class="btnzip">
        버튼모음
        <div id="app">
          <router-link to="/shot">다시찍기 shot으로</router-link>

          <router-view />
        </div>
      </div>
    </div>
    <div class="rightcon">
      <div class="members">참여자</div>
      <div class="chattt">채팅창</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ResultView",
};
</script>

<style></style>
