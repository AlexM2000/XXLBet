package com.epam.xxlbet.milto.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Model of match bet.
 *
 * @author Aliaksei Milto
 */
public class Bet {
    private Long id;
    private Long matchId;
    private Long resultId;
    private Date dateCreated;
    private BigDecimal sum;
    private Long expectedWinnerId;
    private Long userId;

    public Bet() {
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", resultId=" + resultId +
                ", dateCreated=" + dateCreated +
                ", sum=" + sum +
                ", expectedWinnerId=" + expectedWinnerId +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Long getExpectedWinnerId() {
        return expectedWinnerId;
    }

    public void setExpectedWinnerId(Long expectedWinnerId) {
        this.expectedWinnerId = expectedWinnerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
