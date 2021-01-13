package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.requestandresponsebody.CreateMatchRequest;

/**
 * MatchRequestToMatchPopulator.
 *
 * @author Aliaksei Milto
 */
public class MatchRequestToMatchPopulator implements Populator<CreateMatchRequest, Match> {
    private static MatchRequestToMatchPopulator instance;

    private MatchRequestToMatchPopulator() { }

    public static MatchRequestToMatchPopulator getInstance() {
        if (instance == null) {
            instance = new MatchRequestToMatchPopulator();
        }

        return instance;
    }

    @Override
    public void populate(CreateMatchRequest matchRequest, Match match) {
        match.setTournamentName(matchRequest.getTournament());
        match.setDrawCoefficient(matchRequest.getDrawCoefficient());
        match.setDateStarted(matchRequest.getDateStarted());
    }
}
