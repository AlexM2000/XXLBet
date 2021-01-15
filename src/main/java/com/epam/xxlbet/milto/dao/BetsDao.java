package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Bet;

import java.util.List;

/**
 * Dao layer for Bets entity.
 *
 * @author Aliaksei Milto
 */
public interface BetsDao {

    /**
     * Find all bets created by given user.
     *
     * @param email user email
     * @return all {@link Bet} created by given user
     */
    List<Bet> getBetsHistoryByUser(String email);

    /**
     * Find all bets won by given user.
     *
     * @param email user email
     * @return all {@link Bet} won by given user
     */
    List<Bet> getWinningBetsByUser(String email);

    /**
     * Find all bets lost by given user.
     *
     * @param email user email
     * @return all {@link Bet} lost by given user
     */
    List<Bet> getDefeatBetsByUser(String email);

    /**
     * Create bet in database.
     *
     * @param bet Bet
     * @return Created {@link Bet} (taken from database)
     */
    Bet createBet(Bet bet);

    /**
     * Find bet by user id.
     *
     * @param userId user id
     * @return {@link Bet} by given user
     */
    Bet getBetByUserId(Long userId);
}
