package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * A generic wrapper class for the result of a stored procedure execution.
 *
 * <p>This class encapsulates the output of a stored procedure, including:</p>
 * <ul>
 *   <li>A list of result sets returned from the procedure execution</li>
 *   <li>A map of output parameters with their corresponding values</li>
 * </ul>
 */
@Data
public class StoredProcedureResult {

    /**
     * A list of result sets returned by the stored procedure.
     * Each result set is represented as a list of objects.
     *
     * <p>For example, if a stored procedure returns multiple queries,
     * each query result will be a separate list in this collection.</p>
     */
    private List<List<Object>> resultSets;

    /**
     * A map of output parameter names to their corresponding values
     * returned by the stored procedure.
     *
     * <p>This is typically used when stored procedures include OUT or INOUT parameters.</p>
     */
    private Map<String, Object> outParameters;
}
