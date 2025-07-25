package com.innobyteservices.quizzy.api.dto.request;

import com.innobyteservices.quizzy.api.enums.AccessRole;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SignUpRequest {
    private String firstname;
    private String lastname;
    private AccessRole role;
    private String email;
    private String password;
}
