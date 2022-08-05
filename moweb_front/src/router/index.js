import Vue from "vue";
import VueRouter from "vue-router";
// import store from '../store'

<<<<<<< Updated upstream
import MainView from "../views/MainView.vue";
import EnterView from "@/views/EnterView";
import WaitingView from "@/views/WaitingView";
import ShotView from "@/views/ShotView";
import ResultView from "@/views/ResultView";
=======
import HomeView from "../views/HomeView.vue";
import EnterView from "@/views/EnterView";
// import WaitingView from '@/views/WaitingView'
// import ShotView from '@/views/ShotView'
// import ResultView from '@/views/ResultView'
>>>>>>> Stashed changes

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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
