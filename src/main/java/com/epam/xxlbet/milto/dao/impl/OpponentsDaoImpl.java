package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.OpponentsDao;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.populator.impl.ResultSetToOpponentPopulator;

import java.math.BigDecimal;
import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.ADJUST_OPPONENT_TO_MATCH;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_OPPONENTS_BY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_OPPONENTS_BY_TOURNAMENT_NAME;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_OPPONENTS_FROM_MATCH;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_OPPONENT_BY_NAME;

/**
 * OpponentsDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class OpponentsDaoImpl extends AbstractDaoImpl<Opponent> implements OpponentsDao {
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
    public List<Opponent> getOpponentsByTournament(String tournamentName) {
        return executeQuery(SELECT_OPPONENTS_BY_TOURNAMENT_NAME, tournamentName);
    }

    @Override
    public Opponent getOpponentByName(String name) {
        return executeForSingleResult(SELECT_OPPONENT_BY_NAME, name);
    }

    @Override
    public Opponent createOpponent(long id, String name) {
        return null;
    }

    @Override
    public Opponent adjustOpponentToMatch(Opponent opponent, Long matchId, BigDecimal coefficient) {
        executeUpdate(ADJUST_OPPONENT_TO_MATCH, matchId, coefficient.doubleValue(), opponent.getId());
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
