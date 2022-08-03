package com.a507.moweb.api.controller;


import com.a507.moweb.api.service.RoomInfoService;
import com.a507.moweb.db.entity.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  방 관련 API 요청 처리용 컨트롤러
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomInfoService roomInfoService;

    @GetMapping
    public ResponseEntity<RoomInfo> createRoom() {
        String user_name = "미르";
        //DB에 방 생성
        roomInfoService.createRoom(user_name);
        return null;
    }
}
