package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CreateTeamRequest.
 *
 * @author Aliaksei Milto
 */
public class CreateTeamRequest {
    @JsonProperty("tournament")
    private String tournamentName;

    @JsonProperty("name")
    private String opponentName;

    public CreateTeamRequest() { }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
}
