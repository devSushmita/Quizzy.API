package com.innobyteservices.quizzy.api.validators;

import com.innobyteservices.quizzy.api.annotations.Required;
import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;
import com.innobyteservices.quizzy.api.exceptions.FieldRequiredException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for {@link Required} annotation.
 */
public class RequiredValidator implements ConstraintValidator<Required, Object> {

    /**
     * Name of the field being validated.
     */
    private String _field;

    /**
     * Initializes the validator with field name.
     *
     * @param annotation the Required annotation
     */
    @Override
    public void initialize(Required annotation) {
        _field = annotation.fieldName();
    }

    /**
     * Checks if the given value is non-blank.
     *
     * @param value the value to validate
     * @param context validation context
     * @return true if valid; throws {@link FieldRequiredException} if not
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String str && str.isBlank()) {
            context.disableDefaultConstraintViolation();
            String message = null;

            switch (_field.toLowerCase()) {
                case "firstname":
                    message = String.valueOf(ErrorCode.FIRSTNAME_REQUIRED.getCode());
                    break;
                case "lastname":
                    message = String.valueOf(ErrorCode.LASTNAME_REQUIRED.getCode());
                    break;
                case "email":
                    message = String.valueOf(ErrorCode.EMAIL_REQUIRED.getCode());
                    break;
                case "password":
                    message = String.valueOf(ErrorCode.PASSWORD_REQUIRED.getCode());
                    break;
                case "topic_name":
                    message = String.valueOf(ErrorCode.TOPIC_NAME_REQUIRED.getCode());
                    break;
            }

            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
