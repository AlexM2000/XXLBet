package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.MatchResultDao;
import com.epam.xxlbet.milto.dao.impl.MatchResultDaoImpl;
import com.epam.xxlbet.milto.domain.MatchResult;
import com.epam.xxlbet.milto.service.MatchResultService;

/**
 * MatchResultServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class MatchResultServiceImpl implements MatchResultService {
    private static MatchResultServiceImpl instance;
    private MatchResultDao matchResultDao;

    private MatchResultServiceImpl() {
        this.matchResultDao = MatchResultDaoImpl.getInstance();
    }

    public static MatchResultServiceImpl getInstance() {
        if (instance == null) {
            instance = new MatchResultServiceImpl();
        }

        return instance;
    }

    @Override
    public MatchResult createMatchResult(Long winnerId, Long matchId) {
        MatchResult matchResult = new MatchResult();
        matchResult.setMatchId(matchId);
        matchResult.setWinnerId(winnerId);
        return matchResultDao.createMatchResult(matchResult);
    }

    @Override
    public MatchResult getMatchResultByMatchId(Long matchId) {
        return matchResultDao.getMatchResultByMatchId(matchId);
    }
}
