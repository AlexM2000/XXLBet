package xxl.bet.milto.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Objects.equals(id, bet.id) &&
                Objects.equals(matchId, bet.matchId) &&
                Objects.equals(resultId, bet.resultId) &&
                Objects.equals(dateCreated, bet.dateCreated) &&
                Objects.equals(sum, bet.sum) &&
                Objects.equals(expectedWinnerId, bet.expectedWinnerId) &&
                Objects.equals(userId, bet.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId, resultId, dateCreated, sum, expectedWinnerId, userId);
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
