package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.MatchesService;

import java.io.IOException;

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
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        try {
            response.writeJSONValue(matchesService.getMatchesByTournament(request.getParameter("tournament")));
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
