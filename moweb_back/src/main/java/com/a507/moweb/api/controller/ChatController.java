package com.a507.moweb.api.controller;

import com.a507.moweb.common.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final SimpMessageSendingOperations sendingOperations;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) { //입장할 때
            //보내는 사람 가져와서 웹소켓 세션에 username으로 저장
            headerAccessor.getSessionAttributes().put("username", message.getSender());
            //방번호 가져와서 웹소켓 세션에 roomid로 저장
            headerAccessor.getSessionAttributes().put("roomid", message.getRoomId());
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        //ChatController에서 사용자 참여 이벤트를 처리했기 때문에 별다른 동작 없이 log만 남김
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        //연결된 모든 클라이언트에게 사용자 퇴장 이벤트 처리
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        //웹소켓 세션에서 username과 방번호 가져오기
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomid");

        logger.info("User Disconnected : " + username);

        if(username != null) { //username이 존재하면
            ChatMessage message = new ChatMessage(); //메시지 생성
            message.setRoomId(roomId); //방번호
            message.setType(ChatMessage.MessageType.LEAVE); //메시지 종류
            message.setSender(username); //보내는 사람
            message.setMessage(username+"님이 퇴장하였습니다."); //메시지 내용

            messagingTemplate.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
        }
    }
}
