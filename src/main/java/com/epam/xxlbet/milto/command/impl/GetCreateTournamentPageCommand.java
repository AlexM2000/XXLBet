package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.SportService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetCreateTournamentPageCommand.
 *
 * @author Aliakei Milto
 */
public class GetCreateTournamentPageCommand extends AbstractCommand {
    private static final String PAGE = "/create-tournament";
    private SportService sportService;

    public GetCreateTournamentPageCommand(SportService sportService) {
        this.sportService = sportService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        request.setAttribute("sports", sportService.getAllSports());
        return createForwardCommandResult(PAGE);
    }
}
