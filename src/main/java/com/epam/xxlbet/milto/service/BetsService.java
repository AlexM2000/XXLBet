package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.requestandresponsebody.BetResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * BetsService.
 *
 * @author Aliaksei Milto
 */
public interface BetsService {
    List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber);
    List<Bet> getIncompleteBets(String email, String phoneNumber);
    List<BetResponse> getWinningBetsByUser(String email);
    List<BetResponse> getDefeatBetsByUser(String email);
    Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId);
    List<BetResponse> getBetsHistoryByUser(String email);
}
