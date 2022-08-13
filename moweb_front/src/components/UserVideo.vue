<template>
  <div v-if="streamManager" style="position: relative">
    <p style="position: absolute; margin: 0px 0px 0px 10px">
      {{ clientData }}
    </p>
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
