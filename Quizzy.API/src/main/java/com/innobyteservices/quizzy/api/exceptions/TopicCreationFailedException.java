package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Exception thrown when a topic cannot be created due to a server-side failure
 * or a business logic violation.
 *
 * <p>This exception is associated with the {@link ErrorCode#TOPIC_CREATION_FAILED}
 * error code and uses the standardized message defined in
 * {@link ErrorMessage#ERR_TOPIC_CREATION_FAILED}.</p>
 */
public class TopicCreationFailedException extends APIException {

  /**
   * Constructs a new {@code TopicCreationFailedException} with the
   * predefined error message and error code.
   */
  public TopicCreationFailedException() {
    super(ErrorMessage.ERR_TOPIC_CREATION_FAILED, ErrorCode.TOPIC_CREATION_FAILED);
  }
}
