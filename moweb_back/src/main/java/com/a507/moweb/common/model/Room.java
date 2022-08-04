package com.a507.moweb.common.model;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 방 인원에 대한 정보를 저장하는 클래스
 */
@Data
public class Room {
    private Map<String, User> users;    // 키 : 유저 닉네임, 밸류 : 유저정보
    private int room_no;                // 방 번호
    private String url;            // 방 url

    public Room(User user) {
        super();
        this.users = new HashMap<>();
        users.put(user.getUser_name(), user);
        this.room_no = 0;
        this.url = "http://localhost:8080/room/join/";
    }
}
