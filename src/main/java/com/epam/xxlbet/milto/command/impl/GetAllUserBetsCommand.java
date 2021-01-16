package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.BetsService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 *  GetAllUserBetsCommand.
 *
 * @author Aliaksei Milto
 */
public class GetAllUserBetsCommand extends AbstractCommand {
    private BetsService betsService;

    public GetAllUserBetsCommand(BetsService betsService) {
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        return createWriteDirectlyToResponseCommandResult(
                betsService.getBetsHistoryByUser((String) request.getSessionAttribute("login"))
        );
    }
}
