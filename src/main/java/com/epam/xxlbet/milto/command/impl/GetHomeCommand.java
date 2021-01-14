package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.MatchesService;

import java.time.LocalDateTime;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetHomeCommand.
 *
 * @author Aliaksei Milto
 */
public class GetHomeCommand extends AbstractCommand {
    private static final String HOME_PAGE = "/home";
    private MatchesService matchesService;

    public GetHomeCommand(MatchesService matchesService) {
        getLogger().debug("Initializing HomeServlet...");
        this.matchesService = matchesService;
    }

    @Override
    public CommandResult execute(final RequestContext request, final ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());
        request.setAttribute("matches", matchesService.getFutureMatches());
        request.setAttribute("today", LocalDateTime.now());
        return createForwardCommandResult(HOME_PAGE);
    }
}
