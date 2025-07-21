package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

@Data
public class ScoreCardItem {
    private Question question;
    private int answerId;
}
