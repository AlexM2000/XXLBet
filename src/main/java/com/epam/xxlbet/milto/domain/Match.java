package com.epam.xxlbet.milto.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Match {
    private BigDecimal drawCoefficient;
    private String tournamentName;
    private Set<Opponent> opponents;
    private LocalDateTime dateStarted;

    public Match() {
    }

    public BigDecimal getDrawCoefficient() {
        return drawCoefficient;
    }

    public void setDrawCoefficient(final BigDecimal drawCoefficient) {
        this.drawCoefficient = drawCoefficient;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Set<Opponent> getOpponents() {
        return opponents;
    }

    public void setOpponents(final Set<Opponent> opponents) {
        this.opponents = opponents;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(final LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }
}
