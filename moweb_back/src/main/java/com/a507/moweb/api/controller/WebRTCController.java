package com.a507.moweb.api.controller;

import com.a507.moweb.common.model.TestSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080"}, allowCredentials = "true")
public class WebRTCController {
    private final ArrayList<TestSession> sessionIdList;
    private final SimpMessagingTemplate template;
    // 실시간으로 들어온 세션(유저) 감지하여 전체 세션 리스트 반환
    @MessageMapping("/video/joined-room-info")
    private void joinRoom(@Header("simpSessionId") String sessionId, JSONObject ob) {
        // 현재 들어온 세션 저장.
        Map<String, Object> data = new HashMap<>();
        log.info("sessionID : "+sessionId);
        int room_no = (int)ob.get("room_no");
        sessionIdList.add(new TestSession((String) ob.get("user_name"), sessionId));
        data.put("sessionIdList",sessionIdList);
        data.put("action",10);
        template.convertAndSend("/topic/room/"+room_no, data);
    }
    //
    @MessageMapping("/video/signal")
    private void signal(JSONObject ob) {
        int room_no = (int)ob.get("room_no");
        log.info("signal : "+ob.toJSONString());
        // caller의 정보를 소켓으로 쏴준다.
        Map<String, Object> data = new HashMap<>();
        data.put("user_name", ob.get("user_name"));
        data.put("signal", ob.get("signal"));
        data.put("action",11);
        template.convertAndSend("/topic/room/"+room_no,data);
    }

    // caller와 callee의 signaling을 위해 callee 정보를 쏴준다.
    @MessageMapping("/video/callAnswer")
    private void answerCall(JSONObject ob) {
        int room_no = (int)ob.get("room_no");
        log.info("answerCall : "+ob.toJSONString());
        // accepter의 정보를 소켓으로 쏴준다.
        Map<String, Object> data = new HashMap<>();
        data.put("user_name", ob.get("user_name"));
        data.put("signal",ob.get("signal"));
        data.put("from",ob.get("from"));
        data.put("action",12);
        template.convertAndSend("/topic/room/"+room_no,data);
    }


    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {

        String removedID = "";
        Map<String, Object> data= new HashMap<>();
        // close된 세션의 id 저장.
        for (TestSession session : sessionIdList) {
            if (session.getSessionId().equals(event.getSessionId())) {
                removedID = session.getUser_name();
                sessionIdList.remove(session);
                break;
            }
        }
        data.put("action",19);
        data.put("removedID",removedID);
        //종료 세션 id 전달.
        template.convertAndSend("/topic/video/close-session", removedID);

    }
}
