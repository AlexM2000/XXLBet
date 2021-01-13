package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetRegistrationPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetRegistrationPageCommand extends AbstractCommand {
    private static final String REGISTRATION_PAGE = "/registration";

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());

        return createForwardCommandResult(REGISTRATION_PAGE);
    }
}
