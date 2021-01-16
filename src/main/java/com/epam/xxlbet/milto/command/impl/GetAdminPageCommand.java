package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.SportService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetAdminPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetAdminPageCommand extends AbstractCommand {
    private static final String ADMIN_PAGE = "/admin";
    private SportService sportService;

    public GetAdminPageCommand(final SportService sportService) {
        this.sportService = sportService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        request.setAttribute("sports", sportService.getAllSports());
        return createForwardCommandResult(ADMIN_PAGE);
    }
}
