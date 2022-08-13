import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

const STOPM_SERVER_URL = "https://i7a507.p.ssafy.io/moweb-api/ws/moweb";
//const STOPM_SERVER_URL = "http://localhost:8080/moweb-api/ws/moweb";

const stompApi = {
  socket: null,
  stomp: null,
  connect(room_no, user_name, callback) {
    // 콜백함수는 .vue 파일의 methods 부분에 작성 후 넣어주세요
    console.log(`소켓 연결을 시도합니다. 서버 주소: ${STOPM_SERVER_URL}`);
    this.socket = new SockJS(STOPM_SERVER_URL);
    this.stomp = Stomp.over(this.socket);
    this.stomp.connect(
      {},
      () => {
        console.log("소켓 연결 성공");
        this.stomp.subscribe(`/topic/moweb/room/${room_no}`, callback);
        this.enter({ room_no, user_name });
      },
      (error) => {
        console.log("소켓 연결 실패", error);
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
  // shotMode(message) {
  //   this.stomp.send(`/app/shotMode`, JSON.stringify(message));
  // },
  shot(message) {
    this.stomp.send(`/app/shot`, JSON.stringify(message));
  },
  disconnect() {
    this.stomp.disconnect();
  },
};

export default stompApi;
