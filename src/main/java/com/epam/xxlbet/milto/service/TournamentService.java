package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Tournament;

import java.util.List;

/**
 * TournamentService.
 *
 * @author Aliaksei Milto
 */
public interface TournamentService {

    /**
     * Find tournaments of given kind of sport.
     *
     * @param sportName sport name
     * @return {@link Tournament} of given kind of sport
     */
    List<Tournament> getTournamentsBySportName(String sportName);

    /**
     * Find {@link Tournament} by tournament name.
     *
     * @param tournamentName tournament name
     * @return {@link Tournament}
     */
    Tournament getTournamentByTournamentName(String tournamentName);

    /**
     * Delete finished tournaments.
     */
    void deleteAllFinishedTournaments();
}
