package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

public class GetRegistrationPageCommand extends AbstractCommand {
    private static final String REGISTRATION_PAGE = "/views/registration.jsp";

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        return CommandResult.createForwardCommandResult(REGISTRATION_PAGE);
    }
}
