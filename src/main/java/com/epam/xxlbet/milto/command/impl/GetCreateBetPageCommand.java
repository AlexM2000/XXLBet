package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.SportService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetCreateBetPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetCreateBetPageCommand extends AbstractCommand {
    private static final String CREATE_BET_PAGE = "/create-bet";
    private SportService sportService;

    public GetCreateBetPageCommand(final SportService sportService) {
        this.sportService = sportService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        request.setAttribute("sports", sportService.getAllSports());
        return createForwardCommandResult(CREATE_BET_PAGE);
    }
}
