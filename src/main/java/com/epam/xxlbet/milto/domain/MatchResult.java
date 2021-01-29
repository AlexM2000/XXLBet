package com.epam.xxlbet.milto.domain;

/**
 * MatchResult.
 *
 * @author Aliaksei Milto
 */
public class MatchResult {
    private Long id;
    private Long matchId;
    private Long winnerId;

    public MatchResult() {
    }

    @Override
    public String toString() {
        return "MatchResult{"
                + "id="
                + id
                + ", matchId="
                + matchId
                + ", winnerId="
                + winnerId
                + '}';
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

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}
