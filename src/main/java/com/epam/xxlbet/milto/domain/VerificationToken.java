package com.epam.xxlbet.milto.domain;

import java.util.Date;

public class VerificationToken {
    private long id;
    private String token;
    private Date expiresAt;
    private long userId;

    public VerificationToken() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(final Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }
}
