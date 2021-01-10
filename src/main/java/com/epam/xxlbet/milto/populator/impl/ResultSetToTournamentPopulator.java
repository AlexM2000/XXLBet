package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Tournament;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToTournamentPopulator implements ResultSetPopulator<ResultSet, Tournament> {
    private static final String ID = "id";
    private static final String SPORT_ID = "sport_id";
    private static final String NAME = "name";
    private static final String DATE_STARTED = "date_started";
    private static final String DATE_ENDED = "date_ended";

    private static ResultSetToTournamentPopulator instance;

    private ResultSetToTournamentPopulator() { }

    public static ResultSetToTournamentPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToTournamentPopulator();
        }

        return instance;
    }

    @Override
    public Tournament populate(ResultSet source) throws SQLException {
        Tournament tournament = new Tournament();
        tournament.setId(source.getLong(ID));
        tournament.setSportId(source.getLong(SPORT_ID));
        tournament.setName(source.getString(NAME));
        tournament.setDateStarted(source.getTimestamp(DATE_STARTED).toLocalDateTime());
        tournament.setDateEnded(source.getTimestamp(DATE_ENDED).toLocalDateTime());
        return tournament;
    }
}
