package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Holds the result of a stored procedure execution.
 */
@Data
public class StoredProcedureResult {

    /**
     * List of result sets from the procedure.
     * Each set is a list of objects.
     */
    private List<List<Object>> resultSets;

    /**
     * Map of OUT/INOUT parameter names to their values.
     */
    private Map<String, Object> outParameters;
}
