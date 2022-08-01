package com.a507.moweb.common.model;
import lombok.*;

import java.util.Map;

/**
 * 방 인원에 대한 정보를 저장하는 클래스
 */
@Data
public class Room {
    Map<String, User> users;    // 키 : 유저 닉네임, 밸류 : 유저정보
    int room_no;                // 방 번호
    String room_url;            // 방 url
}
