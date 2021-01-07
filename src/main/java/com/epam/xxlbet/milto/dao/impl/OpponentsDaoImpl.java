package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.OpponentsDao;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.populator.impl.ResultSetToOpponentPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_OPPONENTS_BY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_OPPONENTS_FROM_MATCH;

/**
 * OpponentsDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class OpponentsDaoImpl extends AbstractDao<Opponent> implements OpponentsDao {
    private static OpponentsDaoImpl instance;

    private OpponentsDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS, ResultSetToOpponentPopulator.getInstance());
    }

    public static OpponentsDaoImpl getInstance() {
        if (instance == null) {
            instance = new OpponentsDaoImpl();
        }

        return instance;
    }

    @Override
    public Opponent getOpponentById(long id) {
        return executeForSingleResult(SELECT_OPPONENTS_BY_ID, id);
    }

    @Override
    public List<Opponent> getOpponentsByMatchId(long matchId) {
        return executeQuery(SELECT_OPPONENTS_FROM_MATCH, matchId);
    }

    @Override
    public Opponent createOpponent(long id, String name) {
        return null;
    }

    @Override
    public Opponent adjustOpponentToMatch(long matchId, long coefficient) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
