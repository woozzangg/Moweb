package com.a507.moweb.api.controller;

import com.a507.moweb.common.model.TestSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.context.event.EventListener;
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
    // 실시간으로 들어온 세션 감지하여 전체 세션 리스트 반환
    @MessageMapping("/video/joined-room-info")
    @SendTo("/topic/video/joined-room-info")
    private ArrayList<TestSession> joinRoom(@Header("simpSessionId") String sessionId, JSONObject ob) {

        // 현재 들어온 세션 저장.
        sessionIdList.add(new TestSession((String) ob.get("from"), sessionId));

        return sessionIdList;
    }

    // caller의 정보를 다른 callee들에게 쏴준다.
    @MessageMapping("/video/caller-info")
    @SendTo("/topic/video/caller-info")
    private Map<String, Object> caller(JSONObject ob) {

        log.info("caller : "+ob.toJSONString());
        // caller의 정보를 소켓으로 쏴준다.
        Map<String, Object> data = new HashMap<>();
        data.put("toCall", ob.get("toCall"));
        data.put("from", ob.get("from"));
        data.put("signal", ob.get("signal"));

        return data;
    }

    // caller와 callee의 signaling을 위해 callee 정보를 쏴준다.
    @MessageMapping("/video/callee-info")
    @SendTo("/topic/video/callee-info")
    private Map<String, Object> answerCall(JSONObject ob) {

        log.info("answerCall : "+ob.toJSONString());
        // accepter의 정보를 소켓으로 쏴준다.
        Map<String, Object> data = new HashMap<>();
        data.put("signal", ob.get("signal"));
        data.put("from", ob.get("from"));
        data.put("to", ob.get("to"));

        return data;
    }


    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {

        String removedID = "";

        // close된 세션의 id 저장.
        for (TestSession session : sessionIdList) {
            if (session.getSessionId().equals(event.getSessionId())) {
                removedID = session.getId();
                sessionIdList.remove(session);
                break;
            }
        }

        //종료 세션 id 전달.
        template.convertAndSend("/topic/video/close-session", removedID);

    }
}
