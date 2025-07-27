package com.innobyteservices.quizzy.api.validators;

import com.innobyteservices.quizzy.api.annotations.StrongPassword;
import com.innobyteservices.quizzy.api.exceptions.StrongPasswordRequiredException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for {@link StrongPassword} annotation.
 */
public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    /**
     * Checks if the given password is strong.
     *
     * @param password the password to validate
     * @param constraintValidatorContext validation context
     * @return true if strong; throws {@link StrongPasswordRequiredException} if not
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUppercase = true;
            else if (Character.isLowerCase(ch)) hasLowercase = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if ("!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~".indexOf(ch) != -1) hasSpecialChar = true;
        }

        boolean isStrongPassword = hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
        if (isStrongPassword) {
            return true;
        } else {
            throw new StrongPasswordRequiredException();
        }
    }
}
