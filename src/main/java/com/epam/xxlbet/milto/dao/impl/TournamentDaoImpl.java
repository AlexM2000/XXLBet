package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.TournamentDao;
import com.epam.xxlbet.milto.domain.Tournament;
import com.epam.xxlbet.milto.populator.impl.ResultSetToTournamentPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_TOURNAMENTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_TOURNAMENTS_BY_SPORT_NAME;

/**
 * TournamentDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class TournamentDaoImpl extends AbstractDaoImpl<Tournament> implements TournamentDao {
    private static TournamentDaoImpl instance;

    private TournamentDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_TOURNAMENTS, ResultSetToTournamentPopulator.getInstance());
    }

    public static TournamentDaoImpl getInstance() {
        if (instance == null) {
            instance = new TournamentDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Tournament> getTournamentsBySportName(String sportName) {
        return executeQuery(SELECT_TOURNAMENTS_BY_SPORT_NAME, sportName);
    }
}
