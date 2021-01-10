package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Sport;

import java.util.List;

/**
 * SportService.
 *
 * @author Aliaksei Milto
 */
public interface SportService {
    List<Sport> getAllSports();
    Sport getSportByName(String name);
    Sport createSport(String sportName);
}
