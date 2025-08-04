package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Exception thrown when attempting to create a topic that already exists.
 * <p>
 * This exception is mapped to the {@link ErrorCode#TOPIC_ALREADY_EXISTS} error code
 * and uses a standardized message from {@link ErrorMessage#ERR_TOPIC_ALREADY_EXISTS}.
 */
public class TopicAlreadyExistsException extends APIException {

  /**
   * Constructs a new {@code TopicAlreadyExistsException} with a predefined error message and code.
   */
  public TopicAlreadyExistsException() {
    super(ErrorMessage.ERR_TOPIC_ALREADY_EXISTS, ErrorCode.TOPIC_ALREADY_EXISTS);
  }
}
