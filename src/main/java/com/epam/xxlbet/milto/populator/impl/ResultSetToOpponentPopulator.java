package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToOpponentPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToOpponentPopulator implements ResultSetPopulator<Opponent> {
    private static final String ID = "id";
    private static final String MATCH_ID = "match_id";
    private static final String TOURNAMENT_ID = "tournament_id";
    private static final String NAME = "name";
    private static final String COEFFICIENT = "coefficient";
    private static ResultSetToOpponentPopulator instance;

    private ResultSetToOpponentPopulator() { }

    public static ResultSetToOpponentPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToOpponentPopulator();
        }

        return instance;
    }

    @Override
    public Opponent populate(ResultSet source) throws SQLException {
        Opponent opponent = new Opponent();
        opponent.setId(source.getLong(ID));
        opponent.setMatchId(source.getLong(MATCH_ID));
        opponent.setName(source.getString(NAME));
        opponent.setCoefficient(source.getBigDecimal(COEFFICIENT));
        opponent.setTournamentId(source.getLong(TOURNAMENT_ID));
        return opponent;
    }
}
