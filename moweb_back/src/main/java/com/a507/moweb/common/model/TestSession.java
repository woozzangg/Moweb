package com.a507.moweb.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestSession {
    private String user_name;
    private String sessionId;
}
