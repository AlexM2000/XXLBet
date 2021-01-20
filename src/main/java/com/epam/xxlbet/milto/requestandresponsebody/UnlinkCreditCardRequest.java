package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UnlinkCreditCardRequest.
 *
 * @author Aliaksei Milto
 */
public class UnlinkCreditCardRequest {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("number")
    private String number;

    public UnlinkCreditCardRequest() { }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
