package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;

/**
 * Interface defining a base contract for executing stored procedures in the database.
 *
 * <p>Implementations of this interface provide a standardized way to invoke stored procedures
 * with input, output, and input-output parameters and retrieve the results in a structured format.</p>
 */
public interface IBaseRepository {

    /**
     * Executes the specified stored procedure using the provided request object and returns the result.
     *
     * @param procedure the {@link StoredProcedureRequest} containing the stored procedure name and parameters
     * @return a {@link StoredProcedureResult} containing result sets and output parameters from the procedure
     */
    StoredProcedureResult execute(StoredProcedureRequest procedure);
}
