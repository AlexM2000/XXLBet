package com.epam.xxlbet.milto.requestandresponsebody;

import java.math.BigDecimal;
import java.util.Date;

public class BetResponse {
    private String match;
    private BigDecimal sum;
    private BigDecimal coefficient;
    private BigDecimal winningSum;
    private Date dateCreated;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
