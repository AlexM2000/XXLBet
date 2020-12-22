package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.BetsDao;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;
import com.epam.xxlbet.milto.populator.impl.ResultSetToBetPopulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_BETS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BETS_BY_USER_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_INCOMPLETE_BETS_BY_USER_ID;

/**
 * BetsDaoImpl.
 *
 * @author alexm2000
 */
public class BetsDaoImpl extends AbstractDao implements BetsDao {
    private static BetsDaoImpl instance;
    private ResultSetPopulator<ResultSet, Bet> populator;


    private BetsDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_BETS);
        populator = ResultSetToBetPopulator.getInstance();
    }

    public static BetsDaoImpl getInstance() {
        if (instance == null) {
            instance = new BetsDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getAllIncompleteBetsByUser(final String email, final String phoneNumber) {
        List<Bet> bets = new ArrayList<>();

        try(final Connection connection = getConnectionPool().getConnection();) {

            final PreparedStatement statement = connection.prepareStatement(getSqlById(SELECT_INCOMPLETE_BETS_BY_USER_ID));

            statement.setString(1, email);
            statement.setString(2, phoneNumber);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bet bet = populator.populate(resultSet);
                bets.add(bet);
            }

            statement.close();
        } catch (InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getAllIncompleteBetsByUser...", e);
        }

        return bets;
    }

    @Override
    public List<Bet> getBetsHistoryByUser(final String email, final String phoneNumber) {
        List<Bet> bets = new ArrayList<>();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(SELECT_BETS_BY_USER_ID));

            statement.setString(1, email);
            statement.setString(2, phoneNumber);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bet bet = populator.populate(resultSet);
                bets.add(bet);
            }

            statement.close();
        } catch (InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getBetsHistoryByUser...", e);
        }

        return bets;
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
