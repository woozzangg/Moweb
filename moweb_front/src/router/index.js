import Vue from "vue";
import VueRouter from "vue-router";
// import store from '../store'

import MainView from "../views/MainView.vue";
import EnterView from "@/views/EnterView";
import WaitingView from "@/views/WaitingView";
import ShotView from "@/views/ShotView";
import ResultView from "@/views/ResultView";
import PhotoView from "../views/PhotoView.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/enter",
    name: "enter",
    component: EnterView,
  },
  {
    path: "/waiting",
    name: "waiting",
    component: WaitingView,
  },
  {
    path: "/shot",
    name: "shot",
    component: ShotView,
  },
  {
    path: "/result",
    name: "result",
    component: ResultView,
  },
  {
    path: "/webrtc",
    name: "webrtc",
    component: PhotoView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
