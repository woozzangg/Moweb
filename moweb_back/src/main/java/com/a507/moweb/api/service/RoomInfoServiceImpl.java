package com.a507.moweb.api.service;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import com.a507.moweb.db.repository.RoomInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("roomInfoService")
public class RoomInfoServiceImpl implements RoomInfoService{
    @Autowired
    private RoomInfoRepository roomInfoRepository;

    private Map<Integer, Room> rooms = new HashMap<>();
    @Override
    public RoomInfo createRoom(String user_name) {
        User user = new User(user_name, 1);                //호스트 유저 생성 레이어 번호는 1번
        Room room = new Room(user);                                 //방 생성 및 호스트 등록
        RoomInfo roomInfo = new RoomInfo();                         //DB에 저장할 방 정보 생성
        roomInfo.set_active(true);                                  //방 정보 활성화 상태 기본값=true
        roomInfo.setUrl("");                                        //방 정보 URL 기본값=""
        roomInfoRepository.save(roomInfo);                          //DB에 방 정보 저장

        roomInfo.setUrl(room.getRoom_url()+roomInfo.getRoom_no());  //DB에 정상적으로 방 생성시 방 번호를 이용해 URL변경
        roomInfoRepository.save(roomInfo);                          //변경된 URL DB에 저장
        return null;
    }
}
