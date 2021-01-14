package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.MatchResult;

/**
 * MatchResultDao.
 *
 * @author Aliaksei Milto
 */
public interface MatchResultDao {
    MatchResult createMatchResult(MatchResult matchResult);
    MatchResult getMatchResultByMatchId(Long matchId);
}
