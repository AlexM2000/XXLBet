package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;

import java.io.IOException;

public class PostLogoutCommand extends AbstractCommand implements Command {
    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        request.invalidateSession();

        try {
            response.writeJSONValue("ok");
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
