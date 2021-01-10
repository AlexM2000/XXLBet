package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.SportDao;
import com.epam.xxlbet.milto.domain.Sport;
import com.epam.xxlbet.milto.populator.impl.ResultSetToSportPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_SPORTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_SPORTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_ALL_SPORTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_SPORT_BY_NAME;

/**
 * SportDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class SportDaoImpl extends AbstractDaoImpl<Sport> implements SportDao {
    private static SportDaoImpl instance;

    private SportDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_SPORTS, ResultSetToSportPopulator.getInstance());
    }

    public static SportDaoImpl getInstance() {
        if (instance == null) {
            instance = new SportDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Sport> getAllSports() {
        return executeQuery(SELECT_ALL_SPORTS);
    }

    @Override
    public Sport getSportByName(String name) {
        return executeForSingleResult(SELECT_SPORT_BY_NAME, name);
    }

    @Override
    public Sport createSport(String sportName) {
        executeUpdate(INSERT_INTO_SPORTS, sportName);
        return getSportByName(sportName);
    }
}
