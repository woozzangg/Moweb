package com.a507.moweb.api.service;

import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import org.springframework.stereotype.Service;

@Service
public interface RoomInfoService {
    RoomInfo createRoom(User user);
}
