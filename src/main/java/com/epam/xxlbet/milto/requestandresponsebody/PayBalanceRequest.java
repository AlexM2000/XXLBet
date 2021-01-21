package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * PayBalanceRequest.
 *
 * @author Aliaksei Milto
 */
public class PayBalanceRequest {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("money")
    private BigDecimal money;

    public PayBalanceRequest() { }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
