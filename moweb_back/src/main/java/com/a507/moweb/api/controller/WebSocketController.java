package com.a507.moweb.api.controller;

import com.a507.moweb.api.service.RoomService;
import com.a507.moweb.common.model.WebSocketMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.slf4j.Logger;

@RestController
@RequestMapping("/moweb")
@RequiredArgsConstructor
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired
    private RoomService roomService;
    private final SimpMessageSendingOperations sendingOperations;

    /**
     * 채팅방에 유저가 입장하면 입장 메세지를 해당 방 참가자 전원에게 보내준다.
     * 또한 현재 참가중인 인원 유저 전체에 대한 정보를 보낸다.
     * action 번호 0
     */
    @MessageMapping("/enter")
    public void enter(WebSocketMessage message, SimpMessageHeaderAccessor headerAccessor) {
        message.setAction(0);
        if(message.getUser_name() != null && message.getRoom_no() != 0) {
            //보내는 사람 가져와서 웹소켓 세션에 user_name으로 저장
            headerAccessor.getSessionAttributes().put("user_name", message.getUser_name());
            //방번호 가져와서 웹소켓 세션에 room_no로 저장
            headerAccessor.getSessionAttributes().put("room_no", message.getRoom_no());
            //입장 메세지 채팅메세지에 추가
            message.setChat_msg(message.getUser_name()+ "님이 입장하였습니다.");
            message.setUsers(roomService.userList(message.getRoom_no()));

            logger.info("User connected : " + message.getUser_name());
            logger.info("layer change");
            sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
        }
    }

    /**
     * 채팅 메세지가 들어오면 해당 메세지를 방 참가자 전원에게 보내준다.
     * action 번호 1
     */
    @MessageMapping("/chat")
    public void chat(WebSocketMessage message) {
        message.setAction(1);
        logger.info("send message : " + message.getUser_name());
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    /**
     * 유저가 ready버튼을 누르면 해당 유저의 상태를 준비or비준비 상태로 바꾸고 해당 사실을 전원에게 알린다.
     * action 번호 2(유저가 준비되었을때)
     * action 번호 3(유저가 준비 취소하였을때)
     */
    @MessageMapping("/ready")
    public void ready(WebSocketMessage message) {
        roomService.ready(message.getRoom_no(), message.getUser_name(), message.isStatus());
        if(message.isStatus()) {
            message.setAction(2);
            logger.info(message.getUser_name() + " : ready");
        }else {
            message.setAction(3);
            logger.info(message.getUser_name() + " : ydaer");
        }

        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    /**
     * 호스트가 시작 버튼을 누르면 해당 사실을 전원에게 알린다.
     * action 번호 4
     */
    @MessageMapping("/start")
    public void start(WebSocketMessage message) {
        message.setAction(4);
        logger.info("moweb start");
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    /**
     * 호스트가 참가자의 레이어 순서를 변경하면 그 정보를 저장하고 보내준다.
     * action 번호 5
     */
    @MessageMapping("/layer")
    public void layer(WebSocketMessage message) {
        message.setAction(5);
        roomService.layer(message.getRoom_no(), message.getUsers());
        logger.info("layer change");
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    /**
     * 호스트가 배경 화면을 바꾸면 전 멤베에게 보내준다.
     * action 번호 6
     */
    @MessageMapping("/theme")
    public void theme(WebSocketMessage message) {
        message.setAction(6);
        logger.info("theme : " + message.getBg_no());
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    /**
     * 호스트가 촬영 버튼을 누르면 현재 촬영 횟수를 카운트한다
     * action 번호 7
     */
    @MessageMapping("/shot")
    public void shot(WebSocketMessage message) {
        message.setAction(7);
        logger.info("shot_cnt : " + message.getShot_cnt());
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    /**
     * 퇴장 처리를 위한 이벤트 리스너
     * 참가자가 퇴장하면 퇴장 알림을 보내주며 만약 호스트가 퇴장할 경우
     * 모든 참가자에게 퇴장 명령을 보낸다.
     * action 8
     */
    @EventListener
    public void exit(SessionDisconnectEvent event) {
        //연결된 모든 클라이언트에게 사용자 퇴장 이벤트 처리
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        //웹소켓 세션에서 user_name과 방번호 가져오기
        String user_name = (String) headerAccessor.getSessionAttributes().get("user_name");
        int room_no = (int) headerAccessor.getSessionAttributes().get("room_no");
        if(user_name != null) {
            roomService.exit(room_no, user_name); //퇴장 처리

            WebSocketMessage message = new WebSocketMessage();
            if(roomService.isHost(room_no, user_name)) {
                message.setAction(8);
                roomService.finish(room_no);
            }else {
                message.setAction(0);
            }
            message.setRoom_no(room_no);
            message.setUser_name(user_name);
            message.setChat_msg(user_name+ "님이 퇴장하였습니다.");
            message.setUsers(roomService.userList(room_no));
            logger.info("User Disconnected : " + user_name);
            logger.info("layer change");
            headerAccessor.getSessionAttributes().clear();

            sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
        }
    }
}
