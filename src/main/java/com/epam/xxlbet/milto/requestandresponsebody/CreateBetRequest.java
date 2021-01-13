package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * CreateBetRequest.
 *
 * @author Aliaksei Milto
 */
public class CreateBetRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("match_id")
    private Long matchId;
    @JsonProperty("sum")
    private BigDecimal sum;
    @JsonProperty("expected_winner_id")
    private Long expectedWinnerId;

    public CreateBetRequest() { }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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
}
