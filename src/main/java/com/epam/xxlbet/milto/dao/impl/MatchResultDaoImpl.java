package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.MatchResultDao;
import com.epam.xxlbet.milto.domain.MatchResult;
import com.epam.xxlbet.milto.populator.impl.ResultSetToMatchResultPopulator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.CREATE_MATCH_RESULT;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_MATCH_RESULTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_MATCH_RESULT_BY_MATCH;

/**
 * MatchResultDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class MatchResultDaoImpl extends AbstractDaoImpl<MatchResult> implements MatchResultDao {
    private static MatchResultDaoImpl instance;

    private MatchResultDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_MATCH_RESULTS, ResultSetToMatchResultPopulator.getInstance());
    }

    public static MatchResultDaoImpl getInstance() {
        if (instance == null) {
            instance = new MatchResultDaoImpl();
        }

        return instance;
    }

    @Override
    public MatchResult createMatchResult(MatchResult matchResult) {
        executeUpdate(CREATE_MATCH_RESULT, matchResult.getMatchId(), matchResult.getWinnerId());
        return getMatchResultByMatchId(matchResult.getMatchId());
    }

    @Override
    public MatchResult getMatchResultByMatchId(Long matchId) {
        return executeForSingleResult(SELECT_MATCH_RESULT_BY_MATCH, matchId);
    }


}
