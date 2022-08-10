import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

const stompApi = {
  socket: null,
  stomp: null,
  connect(roomNo, userName, callback) {
    this.socket = new SockJS("https://i7a507.p.ssafy.io/moweb-api");
    this.stomp = Stomp.over(socket);
    this.stomp.connect({}, function () {
      this.stomp.subscribe(`/topic/moweb/room/${roomNo}`, callback);
      this.enter(roomNo, userName);
    });
  },
  enter(room_no, user_name) {
    this.stomp.send(
      `/app/moweb/enter`,
      JSON.stringify({
        room_no,
        user_name,
      })
    );
  },
  chat(room_no, user_name, chat_msg) {
    this.stomp.send(
      `/app/moweb/chat`,
      JSON.stringify({
        room_no,
        user_name,
        chat_msg,
      })
    );
  },
  ready(room_no, user_name, status) {
    this.stomp.send(
      `/app/moweb/ready`,
      JSON.stringify({
        room_no,
        user_name,
        status,
      })
    );
  },
  start(room_no) {
    this.stomp.send(
      `/app/moweb/start`,
      JSON.stringify({
        room_no,
      })
    );
  },
  layer(room_no, user_names) {
    this.stomp.send(
      `/app/moweb/layer`,
      JSON.stringify({
        room_no,
        user_names,
      })
    );
  },
  theme(room_no, bg_no) {
    this.stomp.send(
      `/app/moweb/theme`,
      JSON.stringify({
        room_no,
        bg_no,
      })
    );
  },
  shot(room_no, shot_cnt, bg_no) {
    this.stomp.send(
      `/app/moweb/shot`,
      JSON.stringify({
        room_no,
        shot_cnt,
        bg_no,
      })
    );
  },
  exit(room_no, user_name) {
    this.stomp.send(
      `/app/moweb/exit`,
      JSON.stringify({
        room_no,
        user_name,
      })
    );
  },
};

export default stompApi;
