package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetConfirmPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetConfirmPageCommand extends AbstractCommand{
    private static final String CONFIRM_PAGE = "/views/confirm.jsp";

    @Override
    public CommandResult execute(final RequestContext request, final ResponseContext response) throws ServiceException {
        return createForwardCommandResult(CONFIRM_PAGE);
    }
}
