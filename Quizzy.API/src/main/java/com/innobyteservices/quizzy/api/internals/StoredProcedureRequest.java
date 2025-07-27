package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Request model for calling a stored procedure.
 */
@Data
public class StoredProcedureRequest {

    /**
     * Creates a new instance with empty parameter maps/lists.
     */
    public StoredProcedureRequest() {
        inParameters = new HashMap<>();
        outParameters = new ArrayList<>();
        inOutParameters = new HashMap<>();
    }

    /**
     * Stored procedure name.
     */
    private String name;

    /**
     * IN parameters to pass into the procedure.
     */
    private Map<String, Object> inParameters;

    /**
     * OUT parameter names to retrieve after execution.
     */
    private ArrayList<String> outParameters;

    /**
     * INOUT parameters passed in and updated by the procedure.
     */
    private Map<String, Object> inOutParameters;
}
