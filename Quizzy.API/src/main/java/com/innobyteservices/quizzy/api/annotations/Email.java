package com.innobyteservices.quizzy.api.annotations;

import com.innobyteservices.quizzy.api.validators.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to validate that a field contains a valid email address.
 *
 * <p>This custom annotation can be applied to fields of type {@code String}. It is validated
 * using the {@link EmailValidator} class, which checks whether the given string is a valid email
 * format using {@code javax.mail.internet.InternetAddress}.</p>
 *
 * @see EmailValidator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    /**
     * Default validation error message when the email is invalid.
     *
     * @return the error message
     */
    String message() default "Invalid email format";

    /**
     * Allows specification of validation groups to which this constraint belongs.
     *
     * @return array of validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload for clients to specify additional metadata about the constraint.
     *
     * @return array of payload types
     */
    Class<? extends Payload>[] payload() default {};
}
