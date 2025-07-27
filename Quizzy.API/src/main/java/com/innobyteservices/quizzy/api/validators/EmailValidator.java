package com.innobyteservices.quizzy.api.validators;

import com.innobyteservices.quizzy.api.annotations.Email;
import com.innobyteservices.quizzy.api.exceptions.InvalidEmailException;
import jakarta.mail.internet.InternetAddress;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validates fields annotated with {@link Email}.
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    /**
     * Returns true if the input is a valid email.
     *
     * @param email the email to check
     * @param constraintValidatorContext the validation context
     * @return true if valid; throws {@link InvalidEmailException} if not
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return true;
        } catch (Exception ex) {
            throw new InvalidEmailException();
        }
    }
}
