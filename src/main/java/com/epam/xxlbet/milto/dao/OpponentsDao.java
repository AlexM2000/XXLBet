package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Opponent;

import java.util.List;

/**
 * OpponentsDao.
 *
 * @author Aliaksei Milto
 */
public interface OpponentsDao {
    Opponent getOpponentById(long id);
    List<Opponent> getOpponentsByMatchId(long matchId);
    public List<Opponent> getOpponentsByTournament(String tournamentName);
    Opponent getOpponentByName(String name);
    Opponent createOpponent(long id, String name);
    Opponent adjustOpponentToMatch(Opponent opponent, Long matchId);
    void delete(long id);
}
