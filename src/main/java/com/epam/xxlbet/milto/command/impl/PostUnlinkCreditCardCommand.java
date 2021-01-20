package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.UnlinkCreditCardRequest;
import com.epam.xxlbet.milto.service.CreditCardService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostUnlinkCreditCardCommand.
 *
 * @author Aliaksei Milto
 */
public class PostUnlinkCreditCardCommand extends AbstractCommand {
    private CreditCardService creditCardService;

    public PostUnlinkCreditCardCommand(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        creditCardService.unlinkCreditCard(
                getRequestBody(request, UnlinkCreditCardRequest.class)
        );

        return createWriteDirectlyToResponseCommandResult("ok");
    }
}
