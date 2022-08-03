package com.a507.moweb.api.service;

import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;


public interface RoomInfoService {
    RoomInfo createRoom(String user_name);
}
