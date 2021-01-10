package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.UserService;

import static com.epam.xxlbet.milto.command.CommandResult.createRedirectCommandResult;

/**
 * PostConfirmRegistrationCommand.
 *
 * @author Aliaksei Milto
 */
public class PostConfirmRegistrationCommand extends AbstractCommand {
    private static final String INVALID = "/invalid";
    private static final String EXPIRED = "/expired";
    private static final String SUCCESS = "/success";
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
                commandResult = createRedirectCommandResult(INVALID);
                break;
            case EXPIRED:
                commandResult = createRedirectCommandResult(EXPIRED);
                break;
            case SUCCESS:
                commandResult = createRedirectCommandResult(SUCCESS);
                break;
            default:
                throw new ServiceException("Something went wrong during registration confirmation...");
        }

        return commandResult;
    }
}
