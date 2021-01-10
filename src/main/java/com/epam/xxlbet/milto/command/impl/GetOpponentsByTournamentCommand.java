package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.OpponentsService;

import java.io.IOException;

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

        try {
            response.writeJSONValue(opponentsService.getOpponentsByTournament(request.getParameter("tournament")));
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
