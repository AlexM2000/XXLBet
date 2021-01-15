package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Sport;

import java.util.List;

/**
 * SportService.
 *
 * @author Aliaksei Milto
 */
public interface SportService {

    /**
     * Find all sports.
     *
     * @return all sports
     */
    List<Sport> getAllSports();

    /**
     * Find {@link Sport} by name.
     *
     * @param name sport name
     * @return sport
     */
    Sport getSportByName(String name);

    /**
     * Create sport in database.
     *
     * @param sportName sportName
     * @return Created {@link Sport} (taken from database)
     */
    Sport createSport(String sportName);
}
