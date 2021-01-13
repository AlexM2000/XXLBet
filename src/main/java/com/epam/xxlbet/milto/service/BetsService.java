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
    List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber);
    List<Bet> getIncompleteBets(String email, String phoneNumber);
    List<BetResponse> getWinningBetsByUser(String email);
    List<BetResponse> getDefeatBetsByUser(String email);
    Bet createBet(CreateBetRequest createBetRequest);
    List<BetResponse> getBetsHistoryByUser(String email);
}
