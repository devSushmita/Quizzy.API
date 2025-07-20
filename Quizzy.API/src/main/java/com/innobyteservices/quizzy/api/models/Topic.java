package com.innobyteservices.quizzy.api.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Topic {
    private int id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int createdBy;
    private int updatedBy;
}
