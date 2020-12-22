package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.UserService;

public class GetProfileCommand implements Command {
    private static final String PROFILE_PAGE = "/views/profile.jsp";
    private UserService userService;
    private BetsService betsService;

    public GetProfileCommand(final UserService userService, final BetsService betsService) {
        this.userService = userService;
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        User user = userService.getUserById((Long) request.getSessionAttribute("user_id"));

        request.setAttribute("bets", betsService.getBetsHistoryByUser(user.getEmail(), user.getPhoneNumber()));
        request.setAttribute("user", user);

        return CommandResult.createForwardCommandResult(PROFILE_PAGE);
    }
}
