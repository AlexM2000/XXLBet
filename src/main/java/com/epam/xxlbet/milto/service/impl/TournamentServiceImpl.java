package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.TournamentDao;
import com.epam.xxlbet.milto.dao.impl.TournamentDaoImpl;
import com.epam.xxlbet.milto.domain.Tournament;
import com.epam.xxlbet.milto.requestandresponsebody.CreateTournamentRequest;
import com.epam.xxlbet.milto.service.TournamentService;

import java.util.List;

/**
 * TournamentServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class TournamentServiceImpl implements TournamentService {
    private static TournamentServiceImpl instance;
    private TournamentDao tournamentDao;

    private TournamentServiceImpl() {
        this.tournamentDao = TournamentDaoImpl.getInstance();
    }

    public static TournamentServiceImpl getInstance() {
        if (instance == null) {
            instance = new TournamentServiceImpl();
        }

        return instance;
    }

    @Override
    public Tournament createTournament(CreateTournamentRequest request) {
        Tournament tournament = new Tournament();
        tournament.setSportId(request.getSportId());
        tournament.setName(request.getTournamentName());
        return tournamentDao.createTournament(tournament);
    }

    @Override
    public List<Tournament> getTournamentsBySportName(String sportName) {
        return tournamentDao.getTournamentsBySportName(sportName);
    }

    @Override
    public Tournament getTournamentByTournamentName(String tournamentName) {
        return tournamentDao.getTournamentByTournamentName(tournamentName);
    }

    @Override
    public void deleteAllFinishedTournaments() {
        tournamentDao.deleteAllFinishedTournaments();
    }
}
