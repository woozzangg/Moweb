import Vue from "vue";
import VueRouter from "vue-router";

import MainView from "../views/MainView.vue";
import EnterView from "@/views/EnterView";
import WaitingView from "@/views/WaitingView";
import JoinView from "@/views/JoinView.vue";
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
    path: "/room/*",
    name: "join",
    component: JoinView,
  },
];

const router = new VueRouter({
  mode: "history",
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
  base: process.env.BASE_URL,
  routes,
});

export default router;
