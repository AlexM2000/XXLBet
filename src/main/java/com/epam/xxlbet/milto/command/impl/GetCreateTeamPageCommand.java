package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.SportService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetCreateTeamPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetCreateTeamPageCommand implements Command {
    private static final String PAGE = "/create-team";
    private SportService sportService;

    public GetCreateTeamPageCommand(SportService sportService) {
        this.sportService = sportService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        request.setAttribute("sports", sportService.getAllSports());
        return createForwardCommandResult(PAGE);
    }
}
