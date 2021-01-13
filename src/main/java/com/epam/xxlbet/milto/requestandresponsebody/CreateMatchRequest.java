package com.epam.xxlbet.milto.requestandresponsebody;

import com.epam.xxlbet.milto.utils.jackson.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CreateMatchRequest.
 *
 * @author Aliaksei Millto
 */
public class CreateMatchRequest {
    @JsonProperty("tournament")
    private String tournament;
    @JsonProperty("team1")
    private String team1;
    @JsonProperty("team2")
    private String team2;
    @JsonProperty("draw_coefficient")
    private BigDecimal drawCoefficient;
    @JsonProperty("team1_coefficient")
    private BigDecimal team1Coefficient;
    @JsonProperty("team2_coefficient")
    private BigDecimal team2Coefficient;
    @JsonProperty("date_started")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateStarted;

    public CreateMatchRequest() { }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public BigDecimal getDrawCoefficient() {
        return drawCoefficient;
    }

    public void setDrawCoefficient(BigDecimal drawCoefficient) {
        this.drawCoefficient = drawCoefficient;
    }

    public BigDecimal getTeam1Coefficient() {
        return team1Coefficient;
    }

    public void setTeam1Coefficient(BigDecimal team1Coefficient) {
        this.team1Coefficient = team1Coefficient;
    }

    public BigDecimal getTeam2Coefficient() {
        return team2Coefficient;
    }

    public void setTeam2Coefficient(BigDecimal team2Coefficient) {
        this.team2Coefficient = team2Coefficient;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }
}
