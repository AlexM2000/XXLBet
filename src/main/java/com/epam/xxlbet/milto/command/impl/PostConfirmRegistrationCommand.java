package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.UserService;

import static com.epam.xxlbet.milto.command.CommandResult.createRedirectCommandResult;

/**
 * PostConfirmRegistrationCommand.
 *
 * @author Aliaksei Milto
 */
public class PostConfirmRegistrationCommand implements Command {
    private UserService userService;

    public PostConfirmRegistrationCommand(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        String token = request.getParameter("token");
        CommandResult commandResult = null;

        switch (userService.confirmRegistration(token)) {
            case INVALID:
                commandResult = createRedirectCommandResult("views/invalid.jsp");
                break;
            case EXPIRED:
                commandResult = createRedirectCommandResult("views/expired.jsp");
                break;
            case SUCCESS:
                commandResult = createRedirectCommandResult("views/success.jsp");
                break;
            default:
                throw new ServiceException("Something went wrong during registration confirmation...");
        }

        return commandResult;
    }
}
