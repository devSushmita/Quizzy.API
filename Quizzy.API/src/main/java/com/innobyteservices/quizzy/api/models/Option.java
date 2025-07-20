package com.innobyteservices.quizzy.api.models;

import java.sql.Timestamp;

public class Option {
    private int id;
    private int questionId;
    private String value;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp createdBy;
    private Timestamp updatedBy;
}
