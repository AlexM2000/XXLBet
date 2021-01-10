package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Opponent;

import java.util.List;

/**
 * OpponentsService.
 *
 * @author Aliaksei Milto
 */
public interface OpponentsService {
    Opponent getOpponentById(Long id);
    List<Opponent> getOpponentsByMatchId(Long matchId);
    List<Opponent> getOpponentsByTournament(String tournamentName);
}
