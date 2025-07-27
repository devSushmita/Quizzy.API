package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.constants.TokenClaimNames;
import com.innobyteservices.quizzy.api.enums.ClaimType;
import com.innobyteservices.quizzy.api.exceptions.ClaimNotFoundException;
import com.innobyteservices.quizzy.api.internals.Token;
import com.innobyteservices.quizzy.api.internals.TokenPayload;
import com.innobyteservices.quizzy.api.services.interfaces.ITokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ITokenService} interface responsible for
 * handling operations related to JWT (JSON Web Token) generation and validation.
 */

@Component
public class TokenService implements ITokenService {

    @Value("${jwt.secret}")
    private String _secret;

    @Value("${jwt.expiration}")
    private long _expiration;

    private Key _signingKey;

    /**
     * Initializes the HMAC signing key used for signing JWT tokens.
     */
    @PostConstruct
    public void init() {
        this._signingKey = Keys.hmacShaKeyFor(_secret.getBytes());
    }

    /**
     * {@inheritDoc}
     */
    public Token generateToken(TokenPayload payload) {
        Token token = new Token();
        Date issuedAt = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + _expiration);

        String value = Jwts.builder()
            .setSubject(payload.getEmail())
            .setClaims(Map.of(
                TokenClaimNames.UserId, payload.getUserId(),
                TokenClaimNames.Email, payload.getEmail(),
                TokenClaimNames.Role, payload.getRole().getValue()
            ))
            .setIssuedAt(issuedAt)
            .setExpiration(expiresAt)
            .signWith(_signingKey, SignatureAlgorithm.HS256)
            .compact();

        token.setValue(value);
        token.setExpiresAt(expiresAt);
        return token;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> getClaim(String token, ClaimType claimType) {
        Map<String, Object> claims =
            Jwts.parserBuilder()
            .setSigningKey(_signingKey)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return switch (claimType) {
            case ClaimType.All -> claims;
            case ClaimType.UserId -> claims.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(TokenClaimNames.UserId))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            case ClaimType.Email -> claims.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(TokenClaimNames.Email))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            case ClaimType.Role -> claims.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(TokenClaimNames.Role))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            default -> throw new ClaimNotFoundException();
        };
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(_signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expirationDate.before(new Date());
    }
}
