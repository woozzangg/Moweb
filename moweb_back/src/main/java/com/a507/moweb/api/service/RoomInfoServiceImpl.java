package com.a507.moweb.api.service;

import com.a507.moweb.common.model.User;
import com.a507.moweb.db.entity.RoomInfo;
import com.a507.moweb.db.repository.RoomInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomInfoServiceImpl implements RoomInfoService{
    @Autowired
    RoomInfoRepository roomInfoRepository;

    @Override
    public RoomInfo createRoom(User user) {
        return null;
    }
}
