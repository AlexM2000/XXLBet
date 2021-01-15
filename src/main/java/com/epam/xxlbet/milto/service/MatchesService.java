package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Match;

import java.util.List;

/**
 * MatchesService.
 *
 * @author Aliaksei Milto
 */
public interface MatchesService {

    /**
     * Find all not started matches.
     *
     * @return all not yet started matches
     */
    List<Match> getFutureMatches();

    /**
     * Create match in database.
     *
     * @param match {@link Match}
     */
    void createMatch(Match match);

    /**
     * Delete all finished matches.
     */
    void deleteAllFinishedMatches();

    /**
     * Find not started matches for given tournament.
     *
     * @param tournamentName name of the tournament
     * @return List of matches
     */
    List<Match> getMatchesByTournament(String tournamentName);

    /**
     * Find all started and not finished matches.
     *
     * @return all started matches
     */
    List<Match> getAllOnlineAndIncompleteMatches();
}
