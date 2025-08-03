package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates the data required to execute a stored procedure.
 * Includes parameter values, types, and the procedure name.
 */
@Data
public class StoredProcedureRequest {

    /**
     * Initializes an empty stored procedure request with default parameter collections.
     */
    public StoredProcedureRequest() {
        inParameters = new ArrayList<>();
        outParameters = new ArrayList<>();
        inOutParameters = new ArrayList<>();
        parameterTypes = new HashMap<>();
    }

    /**
     * The name of the stored procedure to be executed.
     */
    private String name;

    /**
     * List of IN parameters as key-value pairs.
     */
    private List<Map.Entry<String, Object>> inParameters;

    /**
     * List of OUT parameter names.
     */
    private ArrayList<String> outParameters;

    /**
     * List of INOUT parameters as key-value pairs.
     */
    private List<Map.Entry<String, Object>> inOutParameters;

    /**
     * Map of parameter types, keyed by parameter name.
     */
    private Map<String, Class<?>> parameterTypes;
}
