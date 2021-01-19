package com.epam.xxlbet.milto.domain;

/**
 * Credit cart.
 *
 * @author Aliaksei Milto
 */
public class CreditCard {
    private String number;
    private String thru;
    private String cvv;
    private Long userId;

    public CreditCard() { }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getThru() {
        return thru;
    }

    public void setThru(String thru) {
        this.thru = thru;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
