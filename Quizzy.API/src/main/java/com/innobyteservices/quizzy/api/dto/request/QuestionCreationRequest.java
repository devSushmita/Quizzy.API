package com.innobyteservices.quizzy.api.dto.request;

import java.util.List;

/**
 * Represents the request payload for creating a new quiz question.
 * <p>
 * Contains the question title, difficulty level, marks,
 * available options, and the correct option index.
 */
public class QuestionCreationRequest {

    /**
     * The title or text of the question.
     */
    private String title;

    /**
     * The difficulty level of the question (e.g., 1 = Easy, 2 = Medium, 3 = Hard).
     */
    private Integer level;

    /**
     * The marks assigned to this question.
     */
    private Integer marks;

    /**
     * The list of answer options for the question.
     */
    private List<String> options;

    /**
     * The index of the correct option in the options list (0-based).
     */
    private Integer correctOption;

    // Getters and setters (add if needed)
}
