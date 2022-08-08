package com.a507.moweb.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String roomId; //방번호
    private MessageType type; //메시지 종류
    private String sender; //보내는 사람
    private String message; //메시지 내용

    public enum MessageType {
        CHAT, ENTER, LEAVE //채팅, 입장, 퇴장
    }
}
