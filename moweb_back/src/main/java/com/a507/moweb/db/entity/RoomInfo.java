package com.a507.moweb.db.entity;

import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Data
@Entity(name = "room_info")
public class RoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_no;            //방번호

    private String url;             //방의 url
    private String create_time;     //방 생성 시간
    @Column(name = "is_active")
    private boolean active;      //방의 유효 상태 기본은 true
}
