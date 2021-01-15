package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Opponent;

import java.math.BigDecimal;
import java.util.List;

/**
 * OpponentsDao.
 *
 * @author Aliaksei Milto
 */
public interface OpponentsDao {

    /**
     * Find {@link Opponent} by id.
     *
     * @param id opponent id.
     * @return {@link Opponent}
     */
    Opponent getOpponentById(long id);

    /**
     * Find opponents by match id.
     *
     * @param matchId match id
     * @return opponents in given match
     */
    List<Opponent> getOpponentsByMatchId(long matchId);

    /**
     * Find opponents by tournament.
     *
     * @param tournamentName Name of the tournament
     * @return opponents in given tournament
     */
    List<Opponent> getOpponentsByTournament(String tournamentName);

    /**
     * Find opponent by name.
     *
     * @param name Name of the opponent
     * @return {@link Opponent}
     */
    Opponent getOpponentByName(String name);

    /**
     * Create opponent in database.
     *
     *
     * @param opponent {@link Opponent}
     * @return Created {@link Opponent} (taken from database)
     */
    Opponent createOpponent(Opponent opponent);

    /**
     * Set opponent to match and set coefficient for this match.
     *
     * @param opponent {@link Opponent}
     * @param matchId match id
     * @param coefficient coefficient
     */
    Opponent adjustOpponentToMatch(Opponent opponent, Long matchId, BigDecimal coefficient);

    /**
     * Delete opponent
     *
     * @param id opponent id
     */
    void delete(long id);
}
