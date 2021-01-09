package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

import java.io.IOException;

public class PostLogoutCommand extends AbstractCommand {
    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        request.invalidateSession();

        try {
            response.writeValue("ok");
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
