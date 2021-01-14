package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.MatchResult;

/**
 * MatchResultService
 *
 * @author Aliaksei Milto
 */
public interface MatchResultService {
    MatchResult createMatchResult(Long winnerId, Long matchId);
    MatchResult getMatchResultByMatchId(Long matchId);
}
