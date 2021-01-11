package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Match;

import java.util.List;

/**
 * MatchesService.
 *
 * @author Aliaksei Milto
 */
public interface MatchesService {
    List<Match> getIncompleteMatches();
    void createMatch(Match match);
}
