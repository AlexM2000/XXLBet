package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.BetsDao;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.populator.impl.ResultSetToBetPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_BETS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_BETS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BETS_BY_USER_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_DEFEAT_BETS_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_INCOMPLETE_BETS_BY_USER_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_WINNING_BETS_ID;

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
    public List<Bet> getBetsHistoryByUser(final String email) {
        return executeQuery(SELECT_BETS_BY_USER_ID, email);
    }

    @Override
    public List<Bet> getWinningBetsByUser(String email) {
        return executeQuery(SELECT_WINNING_BETS_ID, email);
    }

    @Override
    public List<Bet> getDefeatBetsByUser(String email) {
        return executeQuery(SELECT_DEFEAT_BETS_ID, email);
    }

    @Override
    public Bet createBet(Bet bet) {
        execute(INSERT_INTO_BETS, bet.getMatchId(), bet.getSum(), bet.getResultId(), bet.getUserId());
        return getBetByUserId(bet.getUserId());
    }

    @Override
    public Bet getBetByUserId(Long userId) {
        return executeForSingleResult(SELECT_BETS_BY_USER_ID, userId);
    }
}
