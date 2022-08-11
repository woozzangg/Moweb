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
            message.setUser_names(roomService.layerList(message.getRoom_no()));

            logger.info("User connected : " + message.getUser_name());
            logger.info("layer change");
            sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
        }
    }

    @MessageMapping("/chat")
    public void chat(WebSocketMessage message) {
        message.setAction(1);
        logger.info("send message : " + message.getUser_name());
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    @MessageMapping("/ready")
    public void ready(WebSocketMessage message) {
        if(message.isStatus()) {
            message.setAction(2);
            message.setAll_ready(roomService.ready((message.getRoom_no()), message.getUser_name(), message.isStatus()));
            logger.info(message.getUser_name() + " : ready // All-ready : " + message.isAll_ready());
        }else {
            message.setAction(3);
            message.setAll_ready(roomService.ready((message.getRoom_no()), message.getUser_name(), message.isStatus()));
            logger.info(message.getUser_name() + " : ydaer");
        }

        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    @MessageMapping("/start")
    public void start(WebSocketMessage message) {
        message.setAction(4);
        logger.info("moweb start");
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    @MessageMapping("/layer")
    public void layer(WebSocketMessage message) {
        message.setAction(5);
        roomService.layer(message.getRoom_no(), message.getUser_names());
        logger.info("layer change");
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    @MessageMapping("/theme")
    public void theme(WebSocketMessage message) {
        message.setAction(6);
        logger.info("theme : " + message.getBg_no());
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    @MessageMapping("/shot")
    public void shot(WebSocketMessage message) {
        message.setAction(7);
        logger.info("shot_cnt : " + message.getShot_cnt());
        sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
    }

    @EventListener
    public void exit(SessionDisconnectEvent event) {
        //연결된 모든 클라이언트에게 사용자 퇴장 이벤트 처리
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        //웹소켓 세션에서 user_name과 방번호 가져오기
        String user_name = (String) headerAccessor.getSessionAttributes().get("user_name");
        int room_no = (int) headerAccessor.getSessionAttributes().get("room_no");
        if(user_name != null) {
            WebSocketMessage message = new WebSocketMessage();
            if(roomService.isHost(room_no, user_name)) {
                message.setAction(8);
            }else {
                message.setAction(0);
            }
            message.setRoom_no(room_no);
            message.setUser_name(user_name);
            message.setChat_msg(user_name+ "님이 퇴장하였습니다.");
            message.setUser_names(roomService.layerList(room_no));
            logger.info("User Disconnected : " + user_name);
            logger.info("layer change");
            headerAccessor.getSessionAttributes().clear();
            roomService.exit(room_no, user_name);

            sendingOperations.convertAndSend("/topic/moweb/room/"+message.getRoom_no(),message);
        }
    }
}
