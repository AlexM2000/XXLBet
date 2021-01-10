package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Tournament;

import java.util.List;

/**
 * TournamentDao.
 *
 * @author Aliaksei Milto
 */
public interface TournamentDao {
    List<Tournament> getTournamentsBySportName(String sportName);
}
