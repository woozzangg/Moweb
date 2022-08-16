<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="#3A3C40"
          large
          dark
          v-bind="attrs"
          v-on="on"
          style="font-weight: 7; letter-spacing: 2px; font-size: 1rem"
        >
          나가기
        </v-btn>
      </template>
      <v-card>
        <v-card-title> {{ msg }} </v-card-title>
        <v-card-text style="font-size: 1.2rem"
          >정말로 나가시겠습니까?</v-card-text
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="#008b99" text @click="dialog = false"> 취소 </v-btn>
          <v-btn color="#008b99" text @click="exitBtn"> 확인 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
export default {
  data() {
    return {
      dialog: false,
      msg: "",
    };
  },
  props: {
    is_admin: Boolean,
  },
  mounted() {
    if (this.is_admin) this.msg = "방장이 나가면 방이 사라집니다.";
  },
  methods: {
    exitBtn() {
      this.dialog = false;
      this.$emit("leaveSession");
    },
  },
};
</script>
