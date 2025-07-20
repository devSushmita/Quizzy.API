package com.innobyteservices.quizzy.api.dto.request;

import com.innobyteservices.quizzy.api.enums.AccessRole;
import lombok.Data;

@Data
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private AccessRole role;
    private String email;
    private String password;
}
