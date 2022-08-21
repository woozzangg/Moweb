<template>
  <div v-if="streamManager" style="position: relative">
    <p style="position: absolute; margin: 3px 0px 0px 10px; font-size: 1.5rem">
      {{ clientData }}
    </p>
    <v-icon
      v-if="!streamManager.stream.audioActive"
      style="position: absolute; color: red; right: 0; margin: 10px 5px"
      >mdi-microphone-off</v-icon
    >
    <ov-video
      :stream-manager="streamManager"
      :client="clientData"
      style="position: relative"
    />
  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },
  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>
