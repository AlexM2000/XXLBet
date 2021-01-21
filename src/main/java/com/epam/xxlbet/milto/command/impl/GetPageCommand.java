package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;

/**
 * ShowPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetPageCommand implements Command {
    private CommandResult result;

    public GetPageCommand(String page) {
        result = CommandResult.createRedirectCommandResult(page);
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        return result;
    }

}
