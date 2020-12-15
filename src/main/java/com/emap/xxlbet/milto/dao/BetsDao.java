package com.emap.xxlbet.milto.dao;

import com.emap.xxlbet.milto.domain.Bet;

import java.math.BigDecimal;
import java.util.List;

public interface BetsDao {
    List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber);
    List<Bet> getBetsHistoryByUser(String email, String phoneNumber);
    List<Bet> getIncompleteBets(String email, String phoneNumber);
    List<Bet> getWinningBets(String email, String phoneNumber);
    List<Bet> getDefeatBets(String email, String phoneNumber);
    Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId);
}
