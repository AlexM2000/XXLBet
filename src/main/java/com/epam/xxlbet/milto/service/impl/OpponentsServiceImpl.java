package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.OpponentsDao;
import com.epam.xxlbet.milto.dao.impl.OpponentsDaoImpl;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.requestandresponsebody.CreateTeamRequest;
import com.epam.xxlbet.milto.service.OpponentsService;
import com.epam.xxlbet.milto.service.TournamentService;

import java.math.BigDecimal;
import java.util.List;

/**
 * OpponentsServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class OpponentsServiceImpl implements OpponentsService {
    private static OpponentsServiceImpl instance;
    private TournamentService tournamentService;
    private OpponentsDao opponentsDao;

    private OpponentsServiceImpl(final OpponentsDao opponentsDao) {
        this.opponentsDao = opponentsDao;
        this.tournamentService = TournamentServiceImpl.getInstance();
    }

    public static OpponentsServiceImpl getInstance() {
        if (instance == null) {
            instance = new OpponentsServiceImpl(OpponentsDaoImpl.getInstance());
        }

        return instance;
    }

    @Override
    public List<Opponent> getOpponentsByMatchId(Long matchId) {
        return opponentsDao.getOpponentsByMatchId(matchId);
    }

    @Override
    public List<Opponent> getOpponentsByTournament(String tournamentName) {
        return opponentsDao.getOpponentsByTournament(tournamentName);
    }

    @Override
    public Opponent getOpponentByName(String name) {
        return opponentsDao.getOpponentByName(name);
    }

    @Override
    public Opponent adjustOpponentToMatch(Opponent opponent, Long matchId, BigDecimal coefficient) {
        return opponentsDao.adjustOpponentToMatch(opponent, matchId, coefficient);
    }

    @Override
    public Opponent createOpponent(CreateTeamRequest request) {
        Opponent opponent = new Opponent();
        opponent.setName(request.getOpponentName());
        opponent.setTournamentId(
                tournamentService.getTournamentByTournamentName(request.getTournamentName()).getId()
        );
        return opponentsDao.createOpponent(opponent);
    }

    @Override
    public Opponent getOpponentById(Long id) {
        return opponentsDao.getOpponentById(id);
    }
}
