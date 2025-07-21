package com.innobyteservices.quizzy.api.dto.request;

import lombok.Data;

@Data
public class AttemptedQuestion {
    private int questionId;
    private int answerId;
}
