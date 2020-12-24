package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.BetsDao;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.populator.impl.ResultSetToBetPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_BETS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BETS_BY_USER_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_INCOMPLETE_BETS_BY_USER_ID;

/**
 * BetsDaoImpl.
 *
 * @author alexm2000
 */
public class BetsDaoImpl extends AbstractDao<Bet> implements BetsDao {
    private static BetsDaoImpl instance;

    private BetsDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_BETS, ResultSetToBetPopulator.getInstance());
    }

    public static BetsDaoImpl getInstance() {
        if (instance == null) {
            instance = new BetsDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getAllIncompleteBetsByUser(final String email, final String phoneNumber) {
        return executeQuery(SELECT_INCOMPLETE_BETS_BY_USER_ID, email, phoneNumber);
    }

    @Override
    public List<Bet> getBetsHistoryByUser(final String email, final String phoneNumber) {
        return executeQuery(SELECT_BETS_BY_USER_ID, email);
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
    public Bet createBet(Bet bet) {
        return null;
    }
}
