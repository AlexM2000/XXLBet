package com.epam.xxlbet.milto.command;

import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

/**
 * Command.
 *
 * @author Aliaksei Milto
 */
public interface Command {

    CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException;

}
