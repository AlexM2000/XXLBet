package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

public class GetLoginPageCommand extends AbstractCommand {
    private static final String LOGIN_PAGE = "/views/login.jsp";

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        return CommandResult.createForwardCommandResult(LOGIN_PAGE);
    }
}
