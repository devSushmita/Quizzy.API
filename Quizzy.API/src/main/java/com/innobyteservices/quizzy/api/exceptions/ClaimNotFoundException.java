package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when a required claim is missing in the JWT or context.
 */
public class ClaimNotFoundException extends APIException {

    /**
     * Creates a new exception with claim-not-found message and code.
     */
    public ClaimNotFoundException() {
        super(ErrorMessage.ERR_CLAIM_NOT_FOUND, ErrorCode.CLAIM_NOT_FOUND);
    }
}
