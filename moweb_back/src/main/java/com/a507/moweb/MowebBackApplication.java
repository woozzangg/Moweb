package com.a507.moweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@SpringBootApplication
public class MowebBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MowebBackApplication.class, args);
    }

}
