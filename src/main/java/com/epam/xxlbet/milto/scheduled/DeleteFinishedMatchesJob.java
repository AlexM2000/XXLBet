package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.service.MatchesService;
import com.epam.xxlbet.milto.service.impl.MatchesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DeleteFinishedMatchesJob.
 * Delete finished matches.
 *
 * @author Aliaksei Milto
 */
public class DeleteFinishedMatchesJob implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteFinishedMatchesJob.class);
    private static DeleteFinishedMatchesJob instance;
    private MatchesService matchesService;

    private DeleteFinishedMatchesJob() {
        this.matchesService = MatchesServiceImpl.getInstance();
    }

    public static DeleteFinishedMatchesJob getInstance() {
        if (instance == null) {
            instance = new DeleteFinishedMatchesJob();
        }
        return instance;
    }

    @Override
    public void run() {
        LOG.debug("Executing DeleteFinishedMatchesJob...");
        matchesService.deleteAllFinishedMatches();
        LOG.debug("Executed DeleteFinishedMatchesJob successfully");
    }
}
