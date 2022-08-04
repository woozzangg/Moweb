package com.a507.moweb.api.service;

import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import org.json.JSONObject;


public interface RoomInfoService {
    RoomInfo createRoom(String req);
    int joinRoom(String req);
}
