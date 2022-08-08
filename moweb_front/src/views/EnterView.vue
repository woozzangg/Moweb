<template>
  <v-form v-model="valid">
    <v-container>
      <v-row>
        <v-col cols="12" md="4">
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            :counter="10"
            label="위치 1"
            required
          ></v-text-field>
        </v-col>

        <v-col cols="12" md="4">
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="위치 2"
            required
          ></v-text-field>
        </v-col>

        <v-col cols="12" md="4">
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="위치 3"
            required
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
export default {
  name: "EnterView",
};
</script>

<style>
.mint {
  background-color: #12d3a9;
}
.choco {
  background-color: #563d34;
}
.background {
  height: 500vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/mainpage.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>
