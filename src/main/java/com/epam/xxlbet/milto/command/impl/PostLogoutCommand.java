package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;

public class PostLogoutCommand extends AbstractCommand {
    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        request.invalidateSession();
        return CommandResult.createWriteDirectlyToResponseCommandResult("ok");
    }
}
