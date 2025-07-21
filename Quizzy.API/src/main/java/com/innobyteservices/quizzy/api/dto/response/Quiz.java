package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

@Data
public class Quiz {
    private int id;
    private String name;
    private int duration;
}
