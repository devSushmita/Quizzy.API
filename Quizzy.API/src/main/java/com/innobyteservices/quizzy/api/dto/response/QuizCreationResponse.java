package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

@Data
public class QuizCreationResponse {


    private Integer id;

    private Integer topicId;

    private String name;

    private Integer duration;

    private Integer totalQuestions;
}
