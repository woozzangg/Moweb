package com.a507.moweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
// WebSocket 서버 활성화
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //클라이언트가 WebSocket 서버에 연결하는데 사용할 Endpoint 등록
        //Endpoint 구성에 withSockJS() 사용
        //SockJS는 WebSocket을 지원하지 않는 브라우저에 Fallback 옵션을 활성화하는데 사용
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트가 메시지를 보낼 때 경로 맨앞에 "/app"이 붙어있으면 Broker로 보내짐
        registry.setApplicationDestinationPrefixes("/app");
        //메시지 받을 때 관련 경로 설정
        //"/topic"이 prefix(api 경로 맨 앞)에 붙은 경우 Broker가 해당 채팅방을 구독하고 있는 클라이언트에게 메시지 전달
        //"/queue"는 1대1 메시징, "/topic"은 1대다 메시징일 때 주로 사용함
        registry.enableSimpleBroker("/topic");
    }
}
