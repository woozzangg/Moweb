package com.a507.moweb.api.service;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface RoomService {
    RoomInfo createRoom(String req);
    int joinRoom(String req);
    void ready(int room_no, String user_name, boolean status);
    void layer(int room_no, User[] users);
    void exit(int room_no, String user_name);
    boolean isHost(int room_no, String user_name);
    User[] userList(int room_no);
    void cantJoin(int room_no);
    boolean makePic(int room_no, int shot_cnt, String bg_code, String imgPath) throws IOException;
}
