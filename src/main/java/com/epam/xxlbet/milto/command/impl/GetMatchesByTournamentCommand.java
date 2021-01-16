package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.MatchesService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * GetMatchesByTournamentCommand.
 *
 * @author Aliaksei Milto
 */
public class GetMatchesByTournamentCommand extends AbstractCommand {
    private MatchesService matchesService;

    public GetMatchesByTournamentCommand(final MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        return createWriteDirectlyToResponseCommandResult(
                matchesService.getMatchesByTournament(request.getParameter("tournament"))
        );
    }
}
