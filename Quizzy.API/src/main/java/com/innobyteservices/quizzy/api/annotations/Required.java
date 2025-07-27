package com.innobyteservices.quizzy.api.annotations;

import com.innobyteservices.quizzy.api.validators.RequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to indicate that a field is required.
 *
 * <p>This annotation is used to validate that a specific field is not null or blank.
 * It is backed by the {@link RequiredValidator}, which performs field-specific validation
 * and throws a custom exception when the field is missing or invalid.</p>
 *
 * <p>Use this annotation on fields in DTOs or entities to enforce mandatory input.</p>
 *
 * @see RequiredValidator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredValidator.class)
public @interface Required {

    /**
     * Specifies the name of the field being validated.
     * Used internally to identify the field for custom exception handling.
     *
     * @return the logical name of the field
     */
    String fieldName();

    /**
     * The default error message to be returned if validation fails.
     *
     * @return the error message
     */
    String message() default "Invalid field";

    /**
     * Allows specification of validation groups.
     *
     * @return array of group classes
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint.
     *
     * @return array of payload types
     */
    Class<? extends Payload>[] payload() default {};
}
