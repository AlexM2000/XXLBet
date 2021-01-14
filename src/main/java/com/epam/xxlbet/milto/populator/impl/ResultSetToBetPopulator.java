package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToBetPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToBetPopulator implements ResultSetPopulator<Bet> {
    private static final String ID = "id";
    private static final String MATCH_ID = "match_id";
    private static final String RESULT_ID = "result_id";
    private static final String DATE_CREATED = "date_created";
    private static final String SUM = "sum";
    private static final String EXPECTED_WINNER_ID = "expected_winner_id";
    private static final String USER_ID = "user_id";

    private static ResultSetToBetPopulator instance;

    private ResultSetToBetPopulator() { }

    public static ResultSetToBetPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToBetPopulator();
        }

        return instance;
    }

    @Override
    public Bet populate(final ResultSet resultSet) throws SQLException {
        Bet bet = new Bet();
        bet.setId(resultSet.getLong(ID));
        bet.setMatchId(resultSet.getLong(MATCH_ID));
        bet.setDateCreated(resultSet.getTimestamp(DATE_CREATED));
        bet.setSum(resultSet.getBigDecimal(SUM));
        bet.setExpectedWinnerId(resultSet.getLong(EXPECTED_WINNER_ID));
        bet.setResultId(resultSet.getLong(RESULT_ID));
        bet.setUserId(resultSet.getLong(USER_ID));
        return bet;
    }
}
