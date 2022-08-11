package com.a507.moweb.api.service;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import com.a507.moweb.db.repository.RoomInfoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("roomInfoService")
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomInfoRepository roomInfoRepository;

    private Map<Integer, Room> rooms;

    @PostConstruct
    private void init() {
        rooms = new HashMap<>();
    }

    @Override
    public RoomInfo createRoom(String req) {
        User user = new User(new JSONObject(req).getString("user_name"), 1);                //호스트 유저 생성 레이어 번호는 1번
        String url = "/room/" + UUID.randomUUID().toString();                  //랜덤 url 생성
        Room room = new Room(user, url);                                 //방 생성 및 호스트 등록
        RoomInfo roomInfo = new RoomInfo();                         //DB에 저장할 방 정보 생성
        roomInfo.setActive(true);                                  //방 정보 활성화 상태 기본값=true
        roomInfo.setUrl(url);                                       //방 url 등록
        LocalDateTime now2 = LocalDateTime.now();                   //방 생성 시각 가져오기
        String now = now2.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        roomInfo.setCreate_time(now);                               //방 생성 시간 입력

        roomInfoRepository.save(roomInfo);                          //DB에 방 정보 저장

        room.setRoom_no(roomInfo.getRoom_no());
        rooms.put(roomInfo.getRoom_no(), room);
        return roomInfo;
    }

    @Override
    public int joinRoom(String req) {
        JSONObject ob = new JSONObject(req);      //가져온 데이터 변환
        int room_no = 0;                          //방번호 변수 생성
        RoomInfo roomInfo = roomInfoRepository.findRoomInfoByUrlAndActive(ob.getString("url"), true);   //해당url을 가지고 활성화 상태인 방 검색
        if(roomInfo != null){                     //해당하는 url에 액티브상태인 방이 있으면
            room_no = roomInfo.getRoom_no();      //방 번호 저장
        }
        String user_name = ob.getString("user_name");   //유저 이름 저장

        //닉네임 중복검사
        if(!rooms.containsKey(room_no)){    //방이 유효 않을때(존재x)
            return -2;
        }else if(rooms.get(room_no).getUsers().size() > 6){
            return -1;
        }else if(rooms.get(room_no).getUsers().containsKey(user_name)) {    //이름 중복일때
            return 0;
        } else {
            User user = new User(user_name, rooms.get(room_no).getUsers().size()+1);   //유저 생성(이름, 레이어(인원수+1))
            rooms.get(room_no).getUsers().put(user_name, user);         //rooms에 유저 추가
            return room_no;
        }
    }

    /**
     * 방번호, 유저이름, 레디 상태를 가져온다
     * 해당 방 번호의 유저 레디 상태를 변경하고 전부 레디상태이면 TRUE 아니면 FALSE를 리턴
     * */
    @Override
    public boolean ready(int room_no, String user_name, boolean status) {
        rooms.get(room_no).getUsers().get(user_name).setStatus(status);
        if(!status) {
            return false;
        }else {
            for(User user : rooms.get(room_no).getUsers().values()) {
                if(!user.isStatus()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void layer(int room_no, String[] user_names) {
        int cnt = 1;
        for(String user_name : user_names) {
            rooms.get(room_no).getUsers().get(user_name).setLayer(cnt++);
        }
    }

    @Override
    public void exit(int room_no, String user_name) {
        int n = rooms.get(room_no).getUsers().get(user_name).getLayer();
        rooms.get(room_no).getUsers().remove(user_name);
        for( User user : rooms.get(room_no).getUsers().values()) {
            int temp = user.getLayer();
            if(temp > n) {
                rooms.get(room_no).getUsers().get(user.getUser_name()).setLayer(temp-1);
            }
        }
    }

    @Override
    public boolean isHost(int room_no, String user_name) {
        if(rooms.get(room_no).getHost_name() == user_name) {
            return true;
        }
        return false;
    }

    @Override
    public String[] layerList(int room_no) {
        int n = rooms.get(room_no).getUsers().size();
        String[] userList = new String[n];
        for (User user : rooms.get(room_no).getUsers().values()) {
            userList[user.getLayer()-1] = user.getUser_name();
        }
        return userList;
    }

    @Override
    public void finish(int room_no) {
        Optional<RoomInfo> opt = roomInfoRepository.findById(room_no);
        if(!opt.isEmpty()){
            RoomInfo roomInfo = opt.get();
            roomInfo.setActive(false);
            roomInfoRepository.save(roomInfo);
        }
    }
}
