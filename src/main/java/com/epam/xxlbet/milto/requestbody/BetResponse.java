package com.epam.xxlbet.milto.requestbody;

import java.math.BigDecimal;

public class BetResponse {
    private String match;
    private BigDecimal sum;
    private BigDecimal coefficient;
    private BigDecimal winningSum;

    public BetResponse() {
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getWinningSum() {
        return winningSum;
    }

    public void setWinningSum(BigDecimal winningSum) {
        this.winningSum = winningSum;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
