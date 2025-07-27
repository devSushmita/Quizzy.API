package com.innobyteservices.quizzy.api.helpers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Helper class for hashing and verifying passwords using a static salt.
 * The salt is injected from the application.properties file.
 */
@Component
public class PasswordHelper {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    @Value("${app.security.password.salt}")
    private String _salt;

    private byte[] saltBytes;

    @PostConstruct
    public void init() {
        this.saltBytes = Base64.getDecoder().decode(_salt);
    }

    /**
     * Hashes a password using the salt from configuration.
     *
     * @param password the plain text password
     * @return the base64 encoded hashed password
     */
    public String hashPassword(String password) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hashed = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    /**
     * Verifies whether a raw password matches the hashed password.
     *
     * @param rawPassword     the plain text password to check
     * @param hashedPassword  the stored hashed password
     * @return true if matched, false otherwise
     */
    public boolean verify(String rawPassword, String hashedPassword) {
        String hashedAttempt = hashPassword(rawPassword);
        return hashedAttempt.equals(hashedPassword);
    }
}
