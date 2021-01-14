package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.MatchResult;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToMatchResultPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToMatchResultPopulator implements ResultSetPopulator<MatchResult> {
    private static final String ID = "id";
    private static final String MATCH_ID = "match_id";
    private static final String WINNER_ID = "winner_id";
    private static ResultSetToMatchResultPopulator instance;

    private ResultSetToMatchResultPopulator() { }

    public static ResultSetToMatchResultPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToMatchResultPopulator();
        }

        return instance;
    }

    @Override
    public MatchResult populate(ResultSet source) throws SQLException {
        MatchResult matchResult = new MatchResult();
        matchResult.setId(source.getLong(ID));
        matchResult.setMatchId(source.getLong(MATCH_ID));
        matchResult.setWinnerId(source.getLong(WINNER_ID));
        return matchResult;
    }
}
