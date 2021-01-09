package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeUserRoleAndStatusRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("role")
    private String role;
    @JsonProperty("status")
    private String status;

    public ChangeUserRoleAndStatusRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
