package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.TournamentService;

import java.io.IOException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * GetTournamentsBySportCommand.
 *
 * @author Aliaksei Milto
 */
public class GetTournamentsBySportCommand extends AbstractCommand {
    private TournamentService tournamentService;

    public GetTournamentsBySportCommand(final TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());
        try {
            response.writeJSONValue(tournamentService.getTournamentsBySportName(request.getParameter("sport")));
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
