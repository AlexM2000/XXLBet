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
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.EqualOpponentsValidator;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostCreateMatchCommand.
 *
 * @author Alikasei Milto
 */
public class PostCreateMatchCommand extends AbstractCommand {
    private static final String ADMIN_PAGE = "/admin";
    private OpponentsService opponentsService;
    private MatchesService matchesService;
    private Validator equalOpponentsValidator;
    private Populator<CreateMatchRequest, Match> populator = MatchRequestToMatchPopulator.getInstance();

    public PostCreateMatchCommand(final OpponentsService opponentsService, final MatchesService matchesService) {
        this.opponentsService = opponentsService;
        this.matchesService = matchesService;
        this.equalOpponentsValidator = EqualOpponentsValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        CreateMatchRequest matchRequest = getRequestBody(request, CreateMatchRequest.class);
        validate(matchRequest, getCurrentLocale(request), equalOpponentsValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            Match match = new Match();
            populator.populate(matchRequest, match);

            Opponent team1 = opponentsService.getOpponentByName(matchRequest.getTeam1());
            Opponent team2 = opponentsService.getOpponentByName(matchRequest.getTeam2());
            team1.setCoefficient(matchRequest.getTeam1Coefficient());
            team2.setCoefficient(matchRequest.getTeam2Coefficient());

            match.setOpponents(java.util.Set.of(team1, team2));

            matchesService.createMatch(match);
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
