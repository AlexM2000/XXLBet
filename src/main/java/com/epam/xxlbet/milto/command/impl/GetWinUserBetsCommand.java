package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.BetsService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * GetWinUserBetsCommand.
 *
 * @author Aliaksei Milto
 */
public class GetWinUserBetsCommand extends AbstractCommand {
    private BetsService betsService;

    public GetWinUserBetsCommand(final BetsService betsService) {
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        return createWriteDirectlyToResponseCommandResult(
                betsService.getWinningBetsByUser((String) request.getSessionAttribute("login"))
        );
    }
}
