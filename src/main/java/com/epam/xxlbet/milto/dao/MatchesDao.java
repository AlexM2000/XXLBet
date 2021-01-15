package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Match;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * MatchesDao.
 *
 * @author Aliaksei Milto
 */
public interface MatchesDao {

    /**
     * Find not started matches for given tournament.
     *
     * @param tournamentName name of the tournament
     * @return List of matches
     */
    List<Match> getMatchesByTournament(String tournamentName);

    /**
     * Create match in database.
     *
     * @param tournamentId tournament id
     * @param dateStarted date of match start
     * @param drawCoefficient draw coefficient
     * @return id of created {@link Match}
     */
    long createMatch(Long tournamentId, BigDecimal drawCoefficient, LocalDateTime dateStarted);

    /**
     * Delete all finished matches.
     */
    void deleteAllFinishedMatches();

    /**
     * Find all not started matches.
     *
     * @return all not yet started matches
     */
    List<Match> getFutureMatches();

    /**
     * Find all started and not finished matches.
     *
     * @return all started matches
     */
    List<Match> getAllOnlineAndIncompleteMatches();
}
