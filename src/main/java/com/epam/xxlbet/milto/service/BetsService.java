package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.requestbody.BetResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * BetsService.
 *
 * @author Aliaksei Milto
 */
public interface BetsService {
    List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber);
    List<Bet> getBetsHistoryByUser(String email, String phoneNumber);
    List<Bet> getIncompleteBets(String email, String phoneNumber);
    List<BetResponse> getWinningBetsByUser(String email, String phoneNumber);
    List<BetResponse> getDefeatBetsByUser(String email, String phoneNumber);
    Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId);
    List<BetResponse> getBetsHistoryByUser(String email);
}
