package com.a507.moweb.api.controller;


import com.a507.moweb.api.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  방 관련 API 요청 처리용 컨트롤러
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomInfoService roomInfoService;

    @PostMapping
    public createRoom
}
