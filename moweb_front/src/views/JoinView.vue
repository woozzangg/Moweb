<template>
  <div></div>
</template>

<script>
export default {
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.$router.replace({
        name: "enter",
        params: { url: this.$route.path },
      });
    },
  },
};
</script>
