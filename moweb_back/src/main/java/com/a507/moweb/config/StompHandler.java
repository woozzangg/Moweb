package com.a507.moweb.config;

import com.a507.moweb.common.model.Room;
import com.a507.moweb.common.model.RoomManager;
import com.a507.moweb.common.model.User;
import com.a507.moweb.common.model.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.io.IOException;

public class StompHandler implements ChannelInterceptor {

    private final Logger log = LoggerFactory.getLogger(StompHandler.class);

    @Autowired
    RoomManager roomManager;

    @Autowired
    UserManager userManager;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if(accessor.getCommand() == StompCommand.DISCONNECT){
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            final User user = userManager.getBySessionId(sessionId);
            final Room room = roomManager.getRoom(user.getRoomName());
            try{
                room.leave(user);
                if (room.getParticipants().isEmpty()) {
                    roomManager.removeRoom(room);
                }
            }catch(IOException e){
                log.debug("user room leave : ", e.getMessage());
            }
        }
        return message;
    }
}
