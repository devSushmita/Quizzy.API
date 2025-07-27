package com.innobyteservices.quizzy.api.annotations;

import com.innobyteservices.quizzy.api.validators.RequiredValidator;
import com.innobyteservices.quizzy.api.validators.StrongPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating that a password is strong.
 *
 * <p>
 * A strong password typically contains at least one uppercase letter, one lowercase letter,
 * one digit, and one special character. This annotation should be applied to fields of type
 * {@code String} and is validated by the {@link RequiredValidator} class.
 * </p>
 *
 * @see RequiredValidator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StrongPasswordValidator.class)
public @interface StrongPassword {

    /**
     * The default error message when the password does not meet the strength criteria.
     *
     * @return the error message
     */
    String message() default "Password should contain uppercase, lowercase, numbers, and special characters.";

    /**
     * Specifies the validation groups this constraint belongs to.
     *
     * @return array of validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Allows clients to associate custom payload objects with the constraint.
     *
     * @return array of payload types
     */
    Class<? extends Payload>[] payload() default {};
}
