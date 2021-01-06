package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Java representation of request body for PostLoginCommand.
 *
 * @author Aliaksei Milto
 */
public class LoginRequest {
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;

    public LoginRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
