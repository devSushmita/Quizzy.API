package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginResponse {
    private String accessToken;
    private Timestamp expiredAt;
    private UserResponse user;
}
