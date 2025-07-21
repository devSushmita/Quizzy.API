package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

@Data
public class UserScore {
    private int userId;
    private String firstname;
    private String lastname;
    private float overallScore;
}
