package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.domain.MatchResult;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.service.MatchResultService;
import com.epam.xxlbet.milto.service.MatchesService;
import com.epam.xxlbet.milto.service.UserInfoService;
import com.epam.xxlbet.milto.service.impl.MatchResultServiceImpl;
import com.epam.xxlbet.milto.service.impl.MatchesServiceImpl;
import com.epam.xxlbet.milto.service.impl.UserInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GenerateMatchResultJob.
 * Generate random match result of random match
 * and update balance of users that bet on completed match.
 *
 * @author Aliaksei Milto
 */
public class GenerateMatchResultJob implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(GenerateMatchResultJob.class);
    private static GenerateMatchResultJob instance;
    private MatchesService matchesService;
    private MatchResultService matchResultService;
    private UserInfoService userInfoService;

    private GenerateMatchResultJob() {
        matchesService = MatchesServiceImpl.getInstance();
        matchResultService = MatchResultServiceImpl.getInstance();
        userInfoService = UserInfoServiceImpl.getInstance();
    }

    public static GenerateMatchResultJob getInstance() {
        if (instance == null) {
            instance = new GenerateMatchResultJob();
        }

        return instance;
    }

    @Override
    public void run() {
        LOG.debug("Executing " + this.getClass());

        List<Match> incompleteMatches = matchesService.getAllOnlineAndIncompleteMatches();

        if (incompleteMatches.size() > 0) {
            Match matchToComplete = (Match) getRandomElement(incompleteMatches);
            LOG.debug("Chose " + matchToComplete.toString());

            Opponent winner = (Opponent) getRandomElement(new ArrayList<>(matchToComplete.getOpponents()));
            LOG.debug("Winner is " + winner.toString());

            MatchResult matchResult = matchResultService.createMatchResult(winner.getId(), winner.getMatchId());
            LOG.debug("MatchResult is " + matchResult.toString());

            userInfoService.updateAllUsersBalanceAfterMatchComplete(matchToComplete.getId(), matchResult.getId());
        } else {
            LOG.debug("There are no complete matches");
        }

        LOG.debug("Executed " + this.getClass() + " successfully");
    }

    /**
     * Get random element from list.
     *
     * @param list List
     * @return Match
     */
    private Object getRandomElement(List list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
