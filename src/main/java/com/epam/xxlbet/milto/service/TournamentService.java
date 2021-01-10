package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Tournament;

import java.util.List;

/**
 * TournamentService.
 *
 * @author Aliaksei Milto
 */
public interface TournamentService {
    List<Tournament> getTournamentsBySportName(String sportName);
    void deleteAllFinishedTournaments();
}
