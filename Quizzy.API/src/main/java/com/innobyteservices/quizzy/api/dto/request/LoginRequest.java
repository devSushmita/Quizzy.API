package com.innobyteservices.quizzy.api.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
