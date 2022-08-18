import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

const STOPM_SERVER_URL = process.env.VUE_APP_STOPM_SERVER_URL;

const stompApi = {
  socket: null,
  stomp: null,
  connect(room_no, user_name, callback) {
    // 콜백함수는 .vue 파일의 methods 부분에 작성 후 넣어주세요
    this.socket = new SockJS(STOPM_SERVER_URL);
    this.stomp = Stomp.over(this.socket);
    this.stomp.debug = () => {
      return;
    };
    this.stomp.connect(
      {},
      () => {
        this.stomp.subscribe(`/topic/moweb/room/${room_no}`, callback);
        this.enter({ room_no, user_name });
      },
      (error) => {
        console.error(error);
        this.connected = false;
      }
    );
  },
  enter(message) {
    this.stomp.send(`/app/enter`, JSON.stringify(message));
  },
  chat(message) {
    this.stomp.send(`/app/chat`, JSON.stringify(message));
  },
  ready(message) {
    this.stomp.send(`/app/ready`, JSON.stringify(message));
  },
  start(message) {
    this.stomp.send(`/app/start`, JSON.stringify(message));
  },
  layer(message) {
    this.stomp.send(`/app/layer`, JSON.stringify(message));
  },
  theme(message) {
    this.stomp.send(`/app/theme`, JSON.stringify(message));
  },
  shotMode(message) {
    this.stomp.send(`/app/shotMode`, JSON.stringify(message));
  },
  shotCountdown(message) {
    this.stomp.send(`/app/shotCountdown`, JSON.stringify(message));
  },
  shot(message) {
    this.stomp.send(`/app/shot`, JSON.stringify(message));
  },
  disconnect() {
    this.stomp.disconnect();
  },
};

export default stompApi;
