package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Bet;

import java.math.BigDecimal;
import java.util.List;

public interface BetsService {
    List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber);
    List<Bet> getBetsHistoryByUser(String email, String phoneNumber);
    List<Bet> getIncompleteBets(String email, String phoneNumber);
    List<Bet> getWinningBets(String email, String phoneNumber);
    List<Bet> getDefeatBets(String email, String phoneNumber);
    Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId);
}
