package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.MatchResult;

/**
 * MatchResultService
 *
 * @author Aliaksei Milto
 */
public interface MatchResultService {

    /**
     * Create match result in database.
     *
     * @param winnerId winner of the match id
     * @param matchId match id
     * @return Created {@link MatchResult} (taken from database)
     */
    MatchResult createMatchResult(Long winnerId, Long matchId);

    /**
     * Find result of the match by match id.
     *
     * @param matchId match id
     * @return {@link MatchResult}
     */
    MatchResult getMatchResultByMatchId(Long matchId);
}
