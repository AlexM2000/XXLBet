package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.requestandresponsebody.BetResponse;
import com.epam.xxlbet.milto.requestandresponsebody.CreateBetRequest;

import java.util.List;

/**
 * BetsService.
 *
 * @author Aliaksei Milto
 */
public interface BetsService {

    /**
     * Find all bets won by given user.
     *
     * @param email user email.
     * @return List of {@link BetResponse} java representation of json response body.
     */
    List<BetResponse> getWinningBetsByUser(String email);

    /**
     * Find all bets lost by given user.
     *
     * @param email user email.
     * @return List of {@link BetResponse} java representation of json response body.
     */
    List<BetResponse> getDefeatBetsByUser(String email);

    /**
     * Create bet in database from {@link CreateBetRequest}.
     *
     * @param createBetRequest {@link CreateBetRequest}
     * @return Created {@link Bet} (taken from database)
     */
    Bet createBet(CreateBetRequest createBetRequest);

    /**
     * Find all bets made by given user.
     *
     * @param email user email.
     * @return List of {@link BetResponse} java representation of json response body.
     */
    List<BetResponse> getBetsHistoryByUser(String email);
}
