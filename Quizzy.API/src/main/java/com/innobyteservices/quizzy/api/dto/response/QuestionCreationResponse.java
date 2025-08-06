package com.innobyteservices.quizzy.api.dto.response;

import java.util.List;

/**
 * DTO representing the response returned after a question is created.
 */
public class QuestionCreationResponse {

    /**
     * The ID of the question (optional – can be internal tracking).
     */
    private Integer id;

    /**
     * The title or content of the question.
     */
    private String title;

    /**
     * The difficulty level of the question (e.g., 1 for easy, 2 for medium).
     */
    private Integer level;

    /**
     * The marks awarded for answering this question correctly.
     */
    private Integer marks;

    /**
     * The unique identifier generated for the newly created question.
     */
    private Integer questionId;

    /**
     * A list of options associated with the question.
     */
    private List<OptionResponse> response;
}
