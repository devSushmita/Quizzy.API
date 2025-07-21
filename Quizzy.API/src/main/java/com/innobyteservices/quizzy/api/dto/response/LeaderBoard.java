package com.innobyteservices.quizzy.api.dto.response;

import java.util.ArrayList;

import lombok.Data;

@Data
public class LeaderBoard {
    private Integer quizId;
    private ArrayList<UserScore> standings;
}
