package com.epam.xxlbet.milto.domain;

import java.time.LocalDateTime;

/**
 * PasswordChangeRequest.
 *
 * @author Aliaksei Milto
 */
public class PasswordChangeRequest {
    private Long id;
    private String token;
    private LocalDateTime expiresAt;
    private Long userId;

    public PasswordChangeRequest() { }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
