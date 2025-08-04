package com.innobyteservices.quizzy.api.annotations;

import com.innobyteservices.quizzy.api.enums.AccessRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that a method or class requires a specific user role.
 * Used to check permissions from JWT before allowing access.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermission {

    /**
     * Required role to access the method or class.
     */
    AccessRole role();
}
