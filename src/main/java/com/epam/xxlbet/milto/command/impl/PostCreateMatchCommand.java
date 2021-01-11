package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.Match;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestandresponsebody.CreateMatchRequest;
import com.epam.xxlbet.milto.service.MatchesService;
import com.epam.xxlbet.milto.service.OpponentsService;

import java.util.HashSet;
import java.util.Set;

import static com.epam.xxlbet.milto.command.CommandResult.createRedirectCommandResult;

/**
 * PostCreateMatchCommand.
 *
 * @author Alikasei Milto
 */
public class PostCreateMatchCommand extends AbstractCommand {
    private OpponentsService opponentsService;
    private MatchesService matchesService;

    public PostCreateMatchCommand(final OpponentsService opponentsService, final MatchesService matchesService) {
        this.opponentsService = opponentsService;
        this.matchesService = matchesService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        CreateMatchRequest matchRequest = getRequestBody(request, CreateMatchRequest.class);
        Match match = new Match();
        match.setTournamentName(matchRequest.getTournament());
        match.setDrawCoefficient(matchRequest.getDrawCoefficient());
        match.setDateStarted(matchRequest.getDateStarted());
        Set<Opponent> opponents = new HashSet<>();
        opponents.add(opponentsService.getOpponentByName(matchRequest.getTeam1()));
        opponents.add(opponentsService.getOpponentByName(matchRequest.getTeam2()));
        match.setOpponents(opponents);
        matchesService.createMatch(match);
        return createRedirectCommandResult("/admin");
    }
}
