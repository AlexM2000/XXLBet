package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetCreateCreditCardPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetCreateCreditCardPageCommand implements Command {
    private static final String PAGE = "/create-credit-card";

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        return createForwardCommandResult(PAGE);
    }
}
