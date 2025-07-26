package com.innobyteservices.quizzy.api.enums;

/**
 * Enum representing the status of a quiz submission.
 *
 * <p>Each status is associated with a numeric code.</p>
 */
public enum SubmissionStatus {

    /**
     * Default status when no submission has started.
     */
    None(0),

    /**
     * Indicates that the quiz is currently in progress.
     */
    InProgress(1),

    /**
     * Indicates that the user has submitted the quiz manually.
     */
    Submitted(2),

    /**
     * Indicates that the quiz was automatically submitted (e.g., time expired).
     */
    AutoSubmitted(3),

    /**
     * Indicates that the submission has expired without completion.
     */
    Expired(4);

    /**
     * Numeric code associated with the submission status.
     */
    private final int code;

    /**
     * Constructor to associate a numeric code with the submission status.
     *
     * @param code the integer code representing the status
     */
    SubmissionStatus(int code) {
        this.code = code;
    }

    /**
     * Returns the numeric code associated with the submission status.
     *
     * @return the integer status code
     */
    public int getCode() {
        return code;
    }
}
