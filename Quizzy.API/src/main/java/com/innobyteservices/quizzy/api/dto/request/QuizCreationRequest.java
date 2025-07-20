package com.innobyteservices.quizzy.api.dto.request;

import lombok.Data;

@Data
public class QuizCreationRequest {
    private String name;
    private int topicId;
    private int duration;
    private int totalQuestions;
}
