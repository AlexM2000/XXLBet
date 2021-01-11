package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.MatchesDao;
import com.epam.xxlbet.milto.dao.impl.MatchesDaoImpl;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.domain.Tournament;
import com.epam.xxlbet.milto.service.MatchesService;
import com.epam.xxlbet.milto.service.OpponentsService;
import com.epam.xxlbet.milto.service.TournamentService;

import java.util.List;

/**
 * MatchesServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class MatchesServiceImpl implements MatchesService {
    private static MatchesServiceImpl instance;
    private TournamentService tournamentService;
    private OpponentsService opponentsService;
    private MatchesDao matchesDao;

    private MatchesServiceImpl() {
        this.tournamentService = TournamentServiceImpl.getInstance();
        this.opponentsService = OpponentsServiceImpl.getInstance();
        this.matchesDao = MatchesDaoImpl.getInstance();
    }

    public static MatchesServiceImpl getInstance() {
        if (instance == null) {
            instance = new MatchesServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Match> getIncompleteMatches() {
        return matchesDao.getIncompleteMatches();
    }

    @Override
    public void createMatch(Match match) {
        Tournament tournament = tournamentService.getTournamentByTournamentName(match.getTournamentName());
        Long matchId = matchesDao.createMatch(tournament.getId(), match.getDrawCoefficient(), match.getDateStarted());
        for (Opponent opponent : match.getOpponents()) {
            opponentsService.adjustOpponentToMatch(opponent, matchId);
        }
    }
}
