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
        getLogger().debug("Executing " + this.getClass());

        switch (userService.confirmRegistration(request.getParameter("token"))) {
            default:
                throw new ServiceException("Something went wrong during registration confirmation...");
            case INVALID:
                return createRedirectCommandResult(INVALID);
            case EXPIRED:
                return createRedirectCommandResult(EXPIRED);
            case SUCCESS:
                return createRedirectCommandResult(SUCCESS);
        }
    }
}
