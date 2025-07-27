package com.innobyteservices.quizzy.api.dto.request;

import com.innobyteservices.quizzy.api.annotations.Email;
import com.innobyteservices.quizzy.api.annotations.Required;
import com.innobyteservices.quizzy.api.annotations.StrongPassword;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing the user sign-up request payload.
 *
 * <p>This class is used to receive and validate input data from clients during user registration.
 * It contains basic personal information and credentials required for creating a new user account.</p>
 *
 * <p>Each field is annotated with {@link Required} to enforce that the values are present and valid
 * before processing. Validation logic is handled by {@code RequiredValidator} which throws
 * appropriate exceptions based on missing or invalid data.</p>
 *
 * <pre>
 * Example JSON payload:
 * {
 *   "firstname": "John",
 *   "lastname": "Doe",
 *   "email": "john.doe@example.com",
 *   "password": "securePassword123"
 * }
 * </pre>
 *
 * @see com.innobyteservices.quizzy.api.annotations.Required
 * @see com.innobyteservices.quizzy.api.validators.RequiredValidator
 */
@Getter
@Setter
public class SignUpRequest {

    /**
     * The first name of the user.
     * This field is required.
     */
    @Required(fieldName = "firstname")
    private String firstname;

    /**
     * The last name of the user.
     * This field is required.
     */
    @Required(fieldName = "lastname")
    private String lastname;

    /**
     * The email address of the user.
     * This field is required and must follow a valid email format.
     */
    @Required(fieldName = "email")
    @Email
    private String email;

    /**
     * The password chosen by the user.
     * This field is required.
     */
    @Required(fieldName = "password")
    @StrongPassword
    private String password;
}
