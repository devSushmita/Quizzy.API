package com.innobyteservices.quizzy.api.dto.request;

import com.innobyteservices.quizzy.api.annotations.Required;
import lombok.Data;

/**
 * Request DTO for creating a quiz.
 * <p>
 * Contains the necessary fields required to create a new quiz,
 * including name, topic ID, duration, and total number of questions.
 * </p>
 */
@Data
public class QuizCreationRequest {

    /**
     * The name/title of the quiz.
     */
    @Required(fieldName = "quiz_name")
    private String name;

    /**
     * The ID of the topic to which the quiz belongs.
     */
    @Required(fieldName = "topicId")
    private int topicId;

    /**
     * The duration of the quiz in seconds.
     */
    @Required(fieldName = "duration")
    private int duration;

    /**
     * The total number of questions in the quiz.
     */
    @Required(fieldName = "total_questions")
    private int totalQuestions;
}
