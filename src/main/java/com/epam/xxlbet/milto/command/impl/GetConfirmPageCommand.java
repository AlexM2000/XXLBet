package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetConfirmPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetConfirmPageCommand extends AbstractCommand {
    private static final String CONFIRM_PAGE = "/confirm";

    @Override
    public CommandResult execute(final RequestContext request, final ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());
        return createForwardCommandResult(CONFIRM_PAGE);
    }
}
