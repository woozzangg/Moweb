package com.a507.moweb.common.model;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserManager {
    private Map<String, User> mapSessionIdUsers = new ConcurrentHashMap<>();
    private Map<String, User> mapNameUsers = new ConcurrentHashMap<>();

    public void addUser(User user){
        mapSessionIdUsers.put(user.getSessionId(), user);
        mapNameUsers.put(user.getName(), user);
    }

    public User getUserBySessionId(String sessionId){
        return mapSessionIdUsers.get(sessionId);
    }

    public User getUserByName(String name){
        return mapNameUsers.get(name);
    }

    public User removeUserBySessionId(String sessionId){
        User user = mapSessionIdUsers.get(sessionId);

        mapSessionIdUsers.remove(user.getSessionId());
        mapNameUsers.remove(user.getName());

        return user;
    }

    public User removeUserByName(String name){
        User user = mapNameUsers.get(name);

        mapSessionIdUsers.remove(user.getSessionId());
        mapNameUsers.remove(user.getName());

        return user;

    }

}
