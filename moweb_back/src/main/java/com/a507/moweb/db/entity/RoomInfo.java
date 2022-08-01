package com.a507.moweb.db.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "room_info")
public class RoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_no;            //방번호

    private String url;             //방의 url
    private boolean is_active;      //방의 유효 상태 기본은 true
}
