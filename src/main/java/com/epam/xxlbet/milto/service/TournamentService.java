package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Tournament;
import com.epam.xxlbet.milto.requestandresponsebody.CreateTournamentRequest;

import java.util.List;

/**
 * TournamentService.
 *
 * @author Aliaksei Milto
 */
public interface TournamentService {

    /**
     * Create tournament in database.
     *
     * @param request {@link CreateTournamentRequest} request body with tournament info
     * @return Created {@link Tournament} (taken from database)
     */
    Tournament createTournament(CreateTournamentRequest request);

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
