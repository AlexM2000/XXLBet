package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Bet;

import java.util.List;


public interface BetsDao {
    List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber);
    List<Bet> getBetsHistoryByUser(String email, String phoneNumber);
    List<Bet> getWinningBetsByUser(String email, String phoneNumber);
    List<Bet> getDefeatBetsByUser(String email, String phoneNumber);
    Bet createBet(Bet bet);
    Bet getBetByUserId(Long userId);
}
