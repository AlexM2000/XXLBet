package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.CreditCardService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetUnlinkCreditCardPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetUnlinkCreditCardPageCommand extends AbstractCommand {
    private static final String PAGE = "/unlink-credit-card";
    private CreditCardService creditCardService;

    public GetUnlinkCreditCardPageCommand(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        request.setAttribute("cards",
                creditCardService.getCreditCardsByUserId((Long) request.getSessionAttribute("user_id"))
        );

        return createForwardCommandResult(PAGE);
    }
}
