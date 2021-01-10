package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.UserInfoService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

public class GetBookmakerPageCommand extends AbstractCommand {
    private static final String BOOKMAKER_PAGE = "/bookmaker";
    private UserInfoService userInfoService;

    public GetBookmakerPageCommand(final UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {

        request.setAttribute("users", userInfoService.getAllUserInfoForAdminPage());
        return createForwardCommandResult(BOOKMAKER_PAGE);
    }
}
