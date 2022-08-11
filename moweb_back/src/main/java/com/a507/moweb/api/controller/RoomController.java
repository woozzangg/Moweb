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

    /**
     * 유저 닉네임을 입력받아 방을 생성하고 생성된 방의 번호를 리턴한다.
     * @param {유저 닉네임} json
     * @return {유저닉네임, 방 번호} json
     */
    @PostMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody String req) {
        //DB에 방 생성후 저장 정보를 리턴
        RoomInfo roomInfo = roomService.createRoom(req);
        JSONObject res = new JSONObject();          //응답 JSON 객체 생성
        res.put("room_no", roomInfo.getRoom_no());  //방번호 저장
        res.put("url", roomInfo.getUrl());          //url 저장
        return new ResponseEntity<String>(res.toString(),HttpStatus.OK);
    }

    /**
     * url을 통해 방 번호를 검색한다. 또한 해당 방의 유효한지, 같은방 유저와 닉네임이 중복인지, 인원수가 차있는지 확인하여 리턴
     * 방 번호가 -2 이면 방이 유효하지 않음(잘못된  url 혹은 방이 종료됨)
     * 방 번호가 -1 이면 인원수 제한초과
     * 방 번호가 0 이면 닉네임 중복
     * @param {유저 닉네임, url} json
     * @return {유저닉네임, 방번호} json
     */
    @PostMapping("/join")
    public ResponseEntity<String> joinRoom(@RequestBody String req) {
        int room_no = roomService.joinRoom(req);    //방번호를 불러온다()
        JSONObject res = new JSONObject();
        res.put("room_no", room_no);
        return new ResponseEntity<String>(res.toString(), HttpStatus.OK);
    }
}
