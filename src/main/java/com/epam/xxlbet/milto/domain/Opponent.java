package com.epam.xxlbet.milto.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Opponent {
    private Long id;
    private Long matchId;
    private String name;
    private BigDecimal coefficient;

    public Opponent() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opponent opponent = (Opponent) o;
        return Objects.equals(name, opponent.name) &&
                Objects.equals(coefficient, opponent.coefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coefficient);
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
}
