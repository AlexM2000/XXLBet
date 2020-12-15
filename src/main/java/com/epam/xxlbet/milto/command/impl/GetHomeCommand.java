package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.MatchesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class GetHomeCommand extends AbstractCommand {
    private static final String HOME_PAGE = "/views/index.jsp";
    private MatchesService matchesService;

    public GetHomeCommand(MatchesService matchesService) {
        getLogger().debug("Initializing HomeServlet...");
        this.matchesService = matchesService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute("matches", matchesService.getIncompleteMatches());
        request.setAttribute("today", LocalDateTime.now());
        return CommandResult.createForwardCommandResult(HOME_PAGE);
    }
}
