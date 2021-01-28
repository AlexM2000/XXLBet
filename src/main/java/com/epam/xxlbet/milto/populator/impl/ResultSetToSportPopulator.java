package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Sport;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToSportPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToSportPopulator implements ResultSetPopulator<Sport> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static ResultSetToSportPopulator instance;

    private ResultSetToSportPopulator() { }

    public static ResultSetToSportPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToSportPopulator();
        }

        return instance;
    }

    @Override
    public Sport populate(ResultSet source) throws SQLException {
        Sport sport = new Sport();
        sport.setId(source.getLong(ID));
        sport.setName(source.getString(NAME));
        return sport;
    }
}
