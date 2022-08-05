package com.a507.moweb.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
    private String roomId; //방번호
    private String roomName; //방제목

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        //UUID.randomUUID()로 중복 없는 고유값 설정
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }
}
