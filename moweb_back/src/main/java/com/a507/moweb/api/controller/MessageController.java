package com.a507.moweb.api.controller;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.common.model.RoomManager;
import com.a507.moweb.common.model.UserManager;
import com.a507.moweb.common.model.User;
import org.kurento.client.IceCandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MessageController {
    @Autowired
    private RoomManager roomManager;

    @Autowired
    private UserManager userManager;

    private final SimpMessagingTemplate template;

    @Autowired
    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/room/join")
    public void join(String roomId, String userName, @Header("simpSessionId") String sessionId) throws IOException {
        Room room = roomManager.getRoom(roomId);
        final User user = room.join(userName, sessionId, template);
        userManager.register(user);
    }

    @MessageMapping("/room/leave")
    public void leave(@Header("simpSessionId") String sessionId) throws IOException {
        User user = userManager.getBySessionId(sessionId);
        final Room room = roomManager.getRoom(user.getRoomName());

        room.leave(user);
        if (room.getParticipants().isEmpty()) {
            roomManager.removeRoom(room);
        }
    }

    @MessageMapping("/room/receiveVideoFrom")
    public void receiveVideoFrom(String userName, String sdpOffer, @Header("simpSessionId") String sessionId) throws IOException {
        final User user = userManager.getBySessionId(sessionId);
        final User sender = userManager.getByName(userName);
        user.receiveVideoFrom(sender, sdpOffer);
    }

    @MessageMapping("/room/onIceCandidate")
    public void onIceCandidate(String candidate, String sdpMid, int sdpMLineIndex, String userName, @Header("simpSessionId") String sessionId) throws IOException {
        final User user = userManager.getBySessionId(sessionId);
        if (user != null) {
            IceCandidate cand = new IceCandidate(candidate, sdpMid, sdpMLineIndex);
            user.addCandidate(cand, userName);
        }
    }

}
