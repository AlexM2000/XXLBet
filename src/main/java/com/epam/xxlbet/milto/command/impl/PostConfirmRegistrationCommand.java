package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostConfirmRegistrationCommand implements Command {
    private UserService userService;

    public PostConfirmRegistrationCommand(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String token = request.getParameter("token");
        CommandResult commandResult = null;

        switch (userService.confirmRegistration(token)) {
            case INVALID:
                commandResult = CommandResult.createRedirectCommandResult("views/invalid.jsp");
                break;
            case EXPIRED:
                commandResult = CommandResult.createRedirectCommandResult("views/expired.jsp");
                break;
            case SUCCESS:
                commandResult = CommandResult.createRedirectCommandResult("views/success.jsp");
                break;
            default:
                throw new ServiceException("Something went wrong during registration confirmation...");
        }

        return commandResult;
    }
}
