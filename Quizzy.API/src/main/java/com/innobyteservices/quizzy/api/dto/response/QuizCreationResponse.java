package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

/**
 * Represents the response returned after successfully creating a quiz.
 *
 * <p>This DTO contains basic information about the newly created quiz,
 * including its ID, name, topic association, duration, and total number of questions.</p>
 */
@Data
public class QuizCreationResponse {

    /** The unique identifier of the created quiz. */
    private Integer id;

    /** The ID of the topic associated with the quiz. */
    private Integer topicId;

    /** The name of the created quiz. */
    private String name;

    /** The duration of the quiz in seconds. */
    private Integer duration;

    /** The total number of questions in the quiz. */
    private Integer totalQuestions;
}
