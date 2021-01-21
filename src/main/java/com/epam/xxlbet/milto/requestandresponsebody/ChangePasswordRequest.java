package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ChangePasswordRequest.
 *
 * @author Aliaksei Milto
 */
public class ChangePasswordRequest {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("password")
    private String password;

    public ChangePasswordRequest() { }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
