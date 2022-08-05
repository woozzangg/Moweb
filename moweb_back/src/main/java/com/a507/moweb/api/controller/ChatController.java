package com.a507.moweb.api.controller;

import com.a507.moweb.common.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessageSendingOperations sendingOperations;

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
}
