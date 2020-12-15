package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRegistrationPageCommand extends AbstractCommand {
    private static final String REGISTRATION_PAGE = "/views/registration.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.createForwardCommandResult(REGISTRATION_PAGE);
    }
}
