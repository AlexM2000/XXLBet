package com.epam.xxlbet.milto.requestandresponsebody;

/**
 * BookmakerPageUserResponse.
 *
 * @author Aliaksei Milto
 */
public class BookmakerPageUserResponse {
    private String email;
    private String phoneNumber;
    private String role;
    private String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
