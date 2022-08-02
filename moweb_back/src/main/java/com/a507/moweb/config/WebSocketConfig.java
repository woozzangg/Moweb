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
        // 클라이언트가 WebSocket 서버에 연결하는데 사용할 Endpoint 등록
        // Endpoint 구성에 withSockJS() 사용
        // SockJS는 WebSocket을 지원하지 않는 브라우저에 Fallback 옵션을 활성화하는데 사용
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 서버로 메시지를 보낼 때 붙여야 하는 prefix url
        registry.setApplicationDestinationPrefixes("/app");
        // 해당 문자열로 시작하는 메시지 주소값을 받아서 처리하는 broker 활성화
        registry.enableSimpleBroker("/topic");
    }
}
