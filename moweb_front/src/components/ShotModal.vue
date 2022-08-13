<template>
  <v-dialog v-model="dialog" width="fit-content" :persistent="!isAdmin">
    <template v-if="isAdmin" v-slot:activator="{ on, attrs }">
      <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
        활영하기
      </v-btn>
    </template>
    <div class="shot-container">
      <div class="shot-photo">
        <slot></slot>
      </div>
      <div class="shot-side">
        <button
          class="shot-button"
          v-if="this.count <= 0"
          @click="onShotButtonClick"
        >
          <img src="@/assets/images/photoCamera.svg" alt="" />
        </button>
        <v-progress-circular
          :rotate="-90"
          :size="200"
          :width="15"
          :value="(count / 10) % 100"
          color="error"
          v-else
        >
          {{ (count / 1000).toFixed(2) }}
        </v-progress-circular>
      </div>
    </div>
  </v-dialog>
</template>

<script>
export default {
  props: ["dialogProp", "isAdmin", "count"],
  data: function () {
    return {
      dialog: false,
    };
  },
  watch: {
    dialogProp() {
      this.dialog = this.dialogProp;
    },
    dialog(newVal, oldVal) {
      if (this.isAdmin && newVal !== oldVal && newVal !== this.dialogProp) {
        this.$emit("sendDialogChange", this.dialog);
      }
    },
  },
  methods: {
    onShotButtonClick() {
      if (this.isAdmin) {
        this.$emit("sendShotCountdown");
      }
    },
  },
};
</script>

<style>
.v-progress-circular {
  font-size: 32px;
}
.v-progress-circular__overlay {
  transition: none;
}
.shot-container {
  background-color: black;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  padding: 20px;
}
.shot-photo {
  text-align: center;
  vertical-align: middle;
}
.shot-side {
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 185px;
  margin-left: 20px;
}
.shot-button {
  width: 150px;
  height: 150px;
  padding: 28px;
  background-color: white;
  border-radius: 50%;
}
.shot-button:hover {
  background-color: lightgray;
}
</style>
