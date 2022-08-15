package com.a507.moweb.api.service;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import com.a507.moweb.db.repository.RoomInfoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service("roomInfoService")
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomInfoRepository roomInfoRepository;

    private Map<Integer, Room> rooms;

    @PostConstruct
    private void init() {
        rooms = new HashMap<>();
    }

    /**
     * 방생성을 위해 호출되는 매서드
     * 유저 정보를 받아 방을 생성한다
     * 생성된 방은 DB에 저장되며 저장된 DB의 방 번호를 받아와
     * 방 번호에 해당하는 room클래스를 생성해 유저를 저장한다.
     */
    @Override
    public RoomInfo createRoom(String req) {
        User user = new User(new JSONObject(req).getString("user_name"), 1);                //호스트 유저 생성 레이어 번호는 1번
        StringBuilder url = new StringBuilder("/room/").append(UUID.randomUUID());   //랜덤 url 생성
        Room room = new Room(user, url.toString());                                 //방 생성 및 호스트 등록
        RoomInfo roomInfo = new RoomInfo();                         //DB에 저장할 방 정보 생성
        roomInfo.setActive(true);                                  //방 정보 활성화 상태 기본값=true
        roomInfo.setUrl(url.toString());                                       //방 url 등록
        ZoneId zoneId = ZoneId.of("Asia/Seoul");                //한국 표준시 기준으로
        ZonedDateTime now2 = ZonedDateTime.now(zoneId);                   //방 생성 시각 가져오기
        String now = now2.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        roomInfo.setCreate_time(now);                               //방 생성 시간 입력

        roomInfoRepository.save(roomInfo);                          //DB에 방 정보 저장

        room.setRoom_no(roomInfo.getRoom_no());
        rooms.put(roomInfo.getRoom_no(), room);
        return roomInfo;
    }

    /**
     * 방 참가를 위해 호출되는 매서드
     * 유저정보를 받아 방에 참가한다
     * url을 통해 DB에서 방 정보를 불러오며 해당하는 방에 대한 유효성 검사를 실시한다.
     * 통과시 해당 방 번호로 입장한다. 또한 room클래스에 유저가 추가된다.
     */
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
        }else if(rooms.get(room_no).getUsers().size() > 5){
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
     * 해당 방 번호의 유저 레디 상태를 변경한다.
     * */
    @Override
    public void ready(int room_no, String user_name, boolean status) {
        rooms.get(room_no).getUsers().get(user_name).setStatus(status);
    }

    @Override
    public void layer(int room_no, User[] users) {
        for(int i = 0; i < users.length; i++) {
            rooms.get(room_no).getUsers().get(users[i].getUser_name()).setLayer(i+1);
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
        if(rooms.get(room_no).getHost_name().equals(user_name)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User[] userList(int room_no) {
        int n = rooms.get(room_no).getUsers().size();
        User[] users = new User[n];
        for (User user : rooms.get(room_no).getUsers().values()) {
            users[user.getLayer()-1] = user;
        }
        return users;
    }

    @Override
    public void cantJoin(int room_no) {
        Optional<RoomInfo> opt = roomInfoRepository.findById(room_no);
        if(!opt.isEmpty()){
            RoomInfo roomInfo = opt.get();
            roomInfo.setActive(false);
            roomInfoRepository.save(roomInfo);
        }
    }

    @Override
    public boolean makePic(int room_no, int shot_cnt, String bg_code) throws IOException {
        int num = ++rooms.get(room_no).getShot_cnts()[shot_cnt-1];
        if(num == rooms.get(room_no).getUsers().size()) {
            String imgName = "canvas_img_"+room_no + "_" + shot_cnt;
            String imgPath = Paths.get("").toAbsolutePath()+ File.separator+"images"+File.separator;
            BufferedImage img = ImageIO.read(new File(imgPath+"_1.png"));
            BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            Graphics2D g = result.createGraphics();
            g.setColor(Color.decode(bg_code));
            g.fillRect(0, 0, img.getWidth(), img.getHeight());
            for(int i = 1; i <= num; i++) {
                img = ImageIO.read(new File(imgPath + "_" + i + ".png"));
                g.drawImage(img,0, 0, null);
            }
            File file = new File(imgPath+imgName+".png");
            ImageIO.write(result,"png",file);
            return true;
        }else {
            return false;
        }
    }
}
