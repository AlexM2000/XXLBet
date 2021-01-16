package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetLoginPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetLoginPageCommand extends AbstractCommand {
    private static final String LOGIN_PAGE = "/login";

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        return createForwardCommandResult(LOGIN_PAGE);
    }
}
