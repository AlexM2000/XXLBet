package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetLinkCreditCardPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetLinkCreditCardPageCommand implements Command {
    private static final String PAGE = "/link-credit-card";

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        return createForwardCommandResult(PAGE);
    }
}