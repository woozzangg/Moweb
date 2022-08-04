package com.a507.moweb.common.model;

import io.openvidu.java.client.OpenViduRole;
import lombok.Data;

@Data
public class User {
    private String sessionId;
    private String name;
    private OpenViduRole role;

    public User(String sessionId, String name, OpenViduRole role) {
        this.sessionId = sessionId;
        this.name = name;
        this.role = role;
    }
}
