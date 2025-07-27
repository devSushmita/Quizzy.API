package com.innobyteservices.quizzy.api.enums;

/**
 * Enum representing the difficulty level of a quiz question.
 *
 * <p>Each level is associated with an integer code.</p>
 */
public enum QuestionLevel {

    /**
     * No specific difficulty level assigned.
     */
    None(0),

    /**
     * Easy level question.
     */
    Easy(1),

    /**
     * Medium level question.
     */
    Medium(2),

    /**
     * Hard level question.
     */
    Hard(3);

    /**
     * Numeric code associated with the question level.
     */
    private final int code;

    /**
     * Constructor to associate a numeric code with the question level.
     *
     * @param code the integer code representing the difficulty level
     */
    QuestionLevel(int code) {
        this.code = code;
    }

    /**
     * Returns the numeric code associated with the question level.
     *
     * @return the integer difficulty code
     */
    public int getCode() {
        return code;
    }
}
