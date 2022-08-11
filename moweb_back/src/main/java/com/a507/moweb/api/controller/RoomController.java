package com.a507.moweb.api.controller;


import com.a507.moweb.api.service.RoomService;
import com.a507.moweb.common.model.Room;
import com.a507.moweb.db.entity.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import java.util.Map;

/**
 *  방 관련 API 요청 처리용 컨트롤러
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody String req) {
        //DB에 방 생성후 저장 정보를 리턴
        RoomInfo roomInfo = roomService.createRoom(req);
        JSONObject res = new JSONObject();          //응답 JSON 객체 생성
        res.put("room_no", roomInfo.getRoom_no());  //방번호 저장
        res.put("url", roomInfo.getUrl());          //url 저장
        return new ResponseEntity<String>(res.toString(),HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinRoom(@RequestBody String req) {
        int room_no = roomService.joinRoom(req);    //방번호를 불러온다()
        JSONObject res = new JSONObject();
        res.put("room_no", room_no);
        return new ResponseEntity<String>(res.toString(), HttpStatus.OK);
    }
}
