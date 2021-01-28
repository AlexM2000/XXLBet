package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DeleteFinishedTournamentsJob.
 * Delete finished tournaments.
 *
 * @author Aliaksei Milto
 */
public class DeleteFinishedTournamentsJob implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteFinishedTournamentsJob.class);
    private static DeleteFinishedTournamentsJob instance;
    private TournamentService tournamentService;

    private DeleteFinishedTournamentsJob() {
        LOG.debug("Creating " + DeleteFinishedTournamentsJob.class);
    }

    public static DeleteFinishedTournamentsJob getInstance() {
        if (instance == null) {
            instance = new DeleteFinishedTournamentsJob();
        }

        return instance;
    }

    @Override
    public void run() {
        LOG.debug("Executing DeleteFinishedTournamentsJob...");
        tournamentService.deleteAllFinishedTournaments();
        LOG.debug("Executed DeleteFinishedTournamentsJob successfully");
    }
}
