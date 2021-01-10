package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.SportDao;
import com.epam.xxlbet.milto.dao.impl.SportDaoImpl;
import com.epam.xxlbet.milto.domain.Sport;
import com.epam.xxlbet.milto.service.SportService;

import java.util.List;

/**
 * SportServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class SportServiceImpl implements SportService {
    private static SportServiceImpl instance;
    private SportDao sportDao;

    private SportServiceImpl() {
        this.sportDao = SportDaoImpl.getInstance();
    }

    public static SportServiceImpl getInstance() {
        if (instance == null) {
            instance = new SportServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Sport> getAllSports() {
        return sportDao.getAllSports();
    }

    @Override
    public Sport getSportByName(String name) {
        return sportDao.getSportByName(name);
    }

    @Override
    public Sport createSport(String sportName) {
        return sportDao.createSport(sportName);
    }
}
