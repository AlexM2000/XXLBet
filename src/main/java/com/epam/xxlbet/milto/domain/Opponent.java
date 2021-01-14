package com.epam.xxlbet.milto.domain;

import java.math.BigDecimal;

public class Opponent {
    private Long id;
    private Long matchId;
    private Long tournamentId;
    private String name;
    private BigDecimal coefficient;

    public Opponent() {
    }

    @Override
    public String toString() {
        return "Opponent{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", tournamentId=" + tournamentId +
                ", name='" + name + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
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

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
