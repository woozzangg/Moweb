import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

const STOPM_SERVER_URL = "https://i7a507.p.ssafy.io/moweb-api/ws/moweb";

const stompApi = {
  socket: null,
  stomp: null,
  connect(roomNo, userName, callback) {
    // 콜백함수는 .vue 파일의 methods 부분에 작성 후 넣어주세요
    this.socket = new SockJS(STOPM_SERVER_URL);
    this.stomp = Stomp.over(this.socket);
    console.log(this.stomp);
    this.stomp.connect({}, () => {
      console.log(this.stomp);
      this.stomp.subscribe(`/topic/moweb/room/${roomNo}`, callback);
      this.enter(roomNo, userName);
    });
  },
  enter(room_no, user_name) {
    this.stomp.send(
      `/app/enter`,
      JSON.stringify({
        room_no,
        user_name,
      })
    );
  },
  chat(room_no, user_name, chat_msg) {
    this.stomp.send(
      `/app/chat`,
      JSON.stringify({
        room_no,
        user_name,
        chat_msg,
      })
    );
  },
  ready(room_no, user_name, status) {
    this.stomp.send(
      `/app/ready`,
      JSON.stringify({
        room_no,
        user_name,
        status,
      })
    );
  },
  start(room_no) {
    this.stomp.send(
      `/app/start`,
      JSON.stringify({
        room_no,
      })
    );
  },
  layer(room_no, user_names) {
    this.stomp.send(
      `/app/layer`,
      JSON.stringify({
        room_no,
        user_names,
      })
    );
  },
  theme(room_no, bg_no) {
    this.stomp.send(
      `/app/theme`,
      JSON.stringify({
        room_no,
        bg_no,
      })
    );
  },
  shot(room_no, shot_cnt, bg_no) {
    this.stomp.send(
      `/app/shot`,
      JSON.stringify({
        room_no,
        shot_cnt,
        bg_no,
      })
    );
  },
};

export default stompApi;
