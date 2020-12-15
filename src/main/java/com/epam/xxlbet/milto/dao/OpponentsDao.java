package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Opponent;

import java.util.List;

public interface OpponentsDao {
    List<Opponent> getOpponentsByMatch(long matchId);
    Opponent createOpponent(long id, String name);
    Opponent adjustOpponentToMatch(long matchId, long coefficient);
    void delete(long id);
}
