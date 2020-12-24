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
    Opponent createOpponent(long id, String name);
    Opponent adjustOpponentToMatch(long matchId, long coefficient);
    void delete(long id);
}
