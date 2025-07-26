package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to execute a stored procedure, including its name and associated parameters.
 *
 * <p>This class is used to encapsulate the different types of parameters that a stored procedure might accept:</p>
 * <ul>
 *     <li><strong>IN parameters</strong> – values passed into the procedure</li>
 *     <li><strong>OUT parameters</strong> – parameter names expected to receive output from the procedure</li>
 *     <li><strong>INOUT parameters</strong> – values passed in and updated as part of the procedure execution</li>
 * </ul>
 */
@Data
public class StoredProcedureRequest {

    /**
     * Initializes a new instance of the {@code StoredProcedureRequest} class.
     */
    public StoredProcedureRequest() {
        inParameters = new HashMap<String, Object>();
        outParameters = new ArrayList<String>();
        inOutParameters = new HashMap<String, Object>();
    }

    /**
     * The name of the stored procedure to be executed.
     */
    private String name;

    /**
     * A map of input (IN) parameters.
     * <p>The key is the parameter name, and the value is the data to be passed into the stored procedure.</p>
     */
    private Map<String, Object> inParameters;

    /**
     * A list of output (OUT) parameter names.
     * <p>Each entry in the list represents the name of an OUT parameter expected from the procedure.
     * The actual values will be retrieved after execution.</p>
     */
    private ArrayList<String> outParameters;

    /**
     * A map of input-output (INOUT) parameters.
     * <p>The key is the parameter name, and the value is the initial value passed into the procedure.
     * These values may be modified during execution and retrieved afterward.</p>
     */
    private Map<String, Object> inOutParameters;
}
