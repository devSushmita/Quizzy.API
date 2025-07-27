package com.innobyteservices.quizzy.api.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Generic wrapper for API responses.
 *
 * @param <T> the type of response data
 */
public class APIResponse<T> {

    /**
     * Payload returned when the request is successful.
     */
    private T data;

    /**
     * Error details when the request fails.
     */
    private ErrorResponse error;

    /**
     * Indicates if the request was successful.
     * True if error is null, false otherwise.
     */
    @JsonGetter("success")
    public boolean isSuccess() {
        return error == null;
    }

    // Getters and setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}
