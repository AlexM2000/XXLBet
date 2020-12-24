package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Opponent;

import java.util.List;

public interface OpponentsService {
    Opponent getOpponentById(Long id);
    List<Opponent> getOpponentsByMatchId(Long matchId);
}
