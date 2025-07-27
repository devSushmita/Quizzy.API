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
     * @param constraintValidatorContext validation context
     * @return true if valid; throws {@link FieldRequiredException} if not
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value instanceof String str && str.isBlank()) {
            switch (_field.toLowerCase()) {
                case "firstname":
                    throw new FieldRequiredException(ErrorMessage.ERR_FIRSTNAME_REQUIRED, ErrorCode.FIRSTNAME_REQUIRED);
                case "lastname":
                    throw new FieldRequiredException(ErrorMessage.ERR_LASTNAME_REQUIRED, ErrorCode.LASTNAME_REQUIRED);
                case "email":
                    throw new FieldRequiredException(ErrorMessage.ERR_EMAIL_REQUIRED, ErrorCode.EMAIL_REQUIRED);
                case "password":
                    throw new FieldRequiredException(ErrorMessage.ERR_PASSWORD_REQUIRED, ErrorCode.PASSWORD_REQUIRED);
            }
        }

        return true;
    }
}
