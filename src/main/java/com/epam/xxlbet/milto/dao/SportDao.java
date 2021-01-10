package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Sport;

import java.util.List;

/**
 * SportDao.
 *
 * @author Aliaksei Milto
 */
public interface SportDao {
    List<Sport> getAllSports();
    Sport getSportByName(String name);
    Sport createSport(String sportName);
}
