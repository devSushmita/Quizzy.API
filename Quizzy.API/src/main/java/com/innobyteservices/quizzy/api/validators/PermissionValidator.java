package com.innobyteservices.quizzy.api.validators;

import com.innobyteservices.quizzy.api.annotations.RequirePermission;
import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.constants.TokenClaimNames;
import com.innobyteservices.quizzy.api.enums.AccessRole;
import com.innobyteservices.quizzy.api.enums.ClaimType;
import com.innobyteservices.quizzy.api.exceptions.UnauthorizedAccessException;
import com.innobyteservices.quizzy.api.services.interfaces.ITokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Aspect that validates access permissions for methods annotated with {@link RequirePermission}.
 *
 * <p>Checks the presence and validity of a JWT token, and ensures the user's role
 * meets the required access level defined in the annotation.</p>
 */
@Aspect
@Component
public class PermissionValidator {

    private final ITokenService _tokenService;

    /**
     * Initializes the validator with the required token service.
     *
     * @param tokenService service used to validate and parse JWT tokens
     */
    public PermissionValidator(ITokenService tokenService) {
        this._tokenService = tokenService;
    }

    /**
     * Performs permission check before the target method executes.
     *
     * @param joinPoint join point for the intercepted method
     * @throws UnauthorizedAccessException if the request is unauthorized
     */
    @Before("@annotation(com.innobyteservices.quizzy.api.annotations.RequirePermission)")
    public void validatePermission(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedAccessException(ErrorMessage.ERR_MISSING_OR_INVALID_AUTH_HEADER);
        }

        String token = authHeader.substring(7); // Remove "Bearer "

        if (_tokenService.isTokenExpired(token)) {
            throw new UnauthorizedAccessException(ErrorMessage.ERR_TOKEN_EXPIRED);
        }

        Map<String, Object> claims = _tokenService.getClaim(token, ClaimType.Role);
        Object roleValue = claims.get(TokenClaimNames.Role);

        if (roleValue == null) {
            throw new UnauthorizedAccessException(ErrorMessage.ERR_ROLE_CLAIM_MISSING);
        }

        AccessRole userRole;
        try {
            userRole = AccessRole.fromValue((Integer) roleValue);
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedAccessException(ErrorMessage.ERR_INVALID_ROLE_VALUE);
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequirePermission annotation = method.getAnnotation(RequirePermission.class);

        AccessRole requiredRole = annotation.role();
        if (userRole.getValue() != requiredRole.getValue()) {
            throw new UnauthorizedAccessException(ErrorMessage.ERR_INSUFFICIENT_PERMISSIONS);
        }
    }
}
