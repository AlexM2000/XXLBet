package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.MatchResult;

/**
 * MatchResultDao.
 *
 * @author Aliaksei Milto
 */
public interface MatchResultDao {

    /**
     * Create match result in database.
     *
     * @param matchResult {@link MatchResult}
     * @return Created {@link MatchResult} (taken from database)
     */
    MatchResult createMatchResult(MatchResult matchResult);

    /**
     * Find result of the match by match id.
     *
     * @param matchId match id
     * @return {@link MatchResult}
     */
    MatchResult getMatchResultByMatchId(Long matchId);
}
