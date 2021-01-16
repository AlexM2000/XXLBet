package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.populator.impl.MatchRequestToMatchPopulator;
import com.epam.xxlbet.milto.requestandresponsebody.CreateMatchRequest;
import com.epam.xxlbet.milto.service.MatchesService;
import com.epam.xxlbet.milto.service.OpponentsService;

import java.util.HashSet;

import static com.epam.xxlbet.milto.command.CommandResult.createRedirectCommandResult;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

/**
 * PostCreateMatchCommand.
 *
 * @author Alikasei Milto
 */
public class PostCreateMatchCommand extends AbstractCommand {
    private static final String ADMIN_PAGE = "/admin";
    private OpponentsService opponentsService;
    private MatchesService matchesService;
    private Populator<CreateMatchRequest, Match> populator = MatchRequestToMatchPopulator.getInstance();

    public PostCreateMatchCommand(final OpponentsService opponentsService, final MatchesService matchesService) {
        this.opponentsService = opponentsService;
        this.matchesService = matchesService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        CreateMatchRequest matchRequest = getRequestBody(request, CreateMatchRequest.class);

        Match match = new Match();
        populator.populate(matchRequest, match);

        Opponent team1 = opponentsService.getOpponentByName(matchRequest.getTeam1());
        Opponent team2 = opponentsService.getOpponentByName(matchRequest.getTeam2());
        team1.setCoefficient(matchRequest.getTeam1Coefficient());
        team2.setCoefficient(matchRequest.getTeam2Coefficient());

        match.setOpponents(unmodifiableSet(new HashSet<>(asList(team1, team2))));

        matchesService.createMatch(match);
        return createRedirectCommandResult(ADMIN_PAGE);
    }
}
