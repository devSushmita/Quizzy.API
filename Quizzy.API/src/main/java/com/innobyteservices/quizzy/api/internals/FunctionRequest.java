package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.List;

/**
 * Represents a request to execute a SQL function with parameters and expected return type.
 *
 * @param <T> the expected return type of the function
 */
@Data
public class FunctionRequest<T> {

    /**
     * The name of the SQL function to execute.
     */
    private String name;

    /**
     * The list of input parameters for the function, in order.
     */
    private List<Object> parameters;

    /**
     * The expected return type class of the function.
     */
    private Class<T> type;
}
