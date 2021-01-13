package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.OpponentsService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * GetOpponentsByTournamentCommand.
 *
 * @author Aliaksei Milto
 */
public class GetOpponentsByTournamentCommand extends AbstractCommand {
    private OpponentsService opponentsService;

    public GetOpponentsByTournamentCommand(final OpponentsService opponentsService) {
        this.opponentsService = opponentsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());

        return createWriteDirectlyToResponseCommandResult(
                opponentsService.getOpponentsByTournament(request.getParameter("tournament"))
        );
    }
}
