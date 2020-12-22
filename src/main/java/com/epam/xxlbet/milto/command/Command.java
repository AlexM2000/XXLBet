package com.epam.xxlbet.milto.command;

import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

public interface Command {

    CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException;

}
