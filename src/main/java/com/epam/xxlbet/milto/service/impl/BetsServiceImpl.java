package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.BetsDao;
import com.epam.xxlbet.milto.dao.impl.BetsDaoImpl;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.service.BetsService;

import java.math.BigDecimal;
import java.util.List;

/**
 * BetsServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class BetsServiceImpl implements BetsService {
    private static BetsServiceImpl instance;
    private BetsDao betsDao;

    private BetsServiceImpl() {
        betsDao = BetsDaoImpl.getInstance();
    }

    public static BetsServiceImpl getInstance() {
        if (instance == null) {
            instance = new BetsServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber) {
        return betsDao.getAllIncompleteBetsByUser(email, phoneNumber);
    }

    @Override
    public List<Bet> getBetsHistoryByUser(String email, String phoneNumber) {
        return betsDao.getBetsHistoryByUser(email, phoneNumber);
    }

    @Override
    public List<Bet> getIncompleteBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getWinningBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getDefeatBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId) {
        return null;
    }
}
