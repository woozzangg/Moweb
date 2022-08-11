package com.a507.moweb.api.service;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.db.entity.RoomInfo;

import java.util.List;
import java.util.Map;


public interface RoomService {
    RoomInfo createRoom(String req);
    int joinRoom(String req);
    boolean ready(int room_no, String user_name, boolean status);
    void layer(int room_no, String[] user_names);
    void exit(int room_no, String user_name);
    boolean isHost(int room_no, String user_name);
    String[] layerList(int room_no);
    void finish(int room_no);
}
