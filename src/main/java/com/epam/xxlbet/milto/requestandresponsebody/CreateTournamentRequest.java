package com.epam.xxlbet.milto.requestandresponsebody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CreateTournamentRequest.
 *
 * @author Aliaksei Milto
 */
public class CreateTournamentRequest {
    @JsonProperty("sport_id")
    private Long sportId;

    @JsonProperty("name")
    private String tournamentName;

    public CreateTournamentRequest() {
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
