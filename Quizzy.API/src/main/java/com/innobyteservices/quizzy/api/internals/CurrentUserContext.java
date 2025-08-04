package com.innobyteservices.quizzy.api.internals;

import com.innobyteservices.quizzy.api.constants.TokenClaimNames;
import com.innobyteservices.quizzy.api.enums.ClaimType;
import com.innobyteservices.quizzy.api.services.interfaces.ITokenService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

/**
 * Extracts and stores the current user's JWT claim data from the HTTP request.
 * <p>
 * This component is request-scoped and available for injection in other beans.
 */
@Component
@RequestScope
@Getter
public class CurrentUserContext {

    /** The current HTTP request object. */
    private final HttpServletRequest _request;

    /** The token service used to parse JWT tokens. */
    private final ITokenService _tokenService;

    /** The user's ID from the JWT claim. */
    private Integer userId;

    /** The user's email from the JWT claim. */
    private String email;

    /** The user's role value from the JWT claim. */
    private Integer role;

    /** Indicates whether the token is valid and claims were successfully extracted. */
    private boolean isValid;

    /**
     * Creates an instance of {@code CurrentUserContext} with injected request and token service.
     *
     * @param request      the current HTTP request
     * @param tokenService the service used to validate and extract claims from JWT
     */
    public CurrentUserContext(HttpServletRequest request, ITokenService tokenService) {
        this._request = request;
        this._tokenService = tokenService;
    }

    /**
     * Initializes the user context by parsing the JWT from the request header and extracting claims.
     */
    @PostConstruct
    private void init() {
        String authHeader = _request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Map<String, Object> claims = _tokenService.getClaim(token, ClaimType.All);

                this.userId = (Integer) claims.get(TokenClaimNames.UserId);
                this.email = (String) claims.get(TokenClaimNames.Email);
                this.role = (Integer) claims.get(TokenClaimNames.Role);
                this.isValid = true;
            } catch (Exception ex) {
                this.isValid = false;
                // Optionally log or rethrow
            }
        } else {
            this.isValid = false;
        }
    }
}
