package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.CreditCard;
import com.epam.xxlbet.milto.service.CreditCardService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.CreditCardCvvValidator;
import com.epam.xxlbet.milto.validator.impl.CreditCardThruValidator;
import com.epam.xxlbet.milto.validator.impl.CreditCartNumberValidator;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostLinkCreditCardCommand.
 *
 * @author Aliaksei Milto
 */
public class PostLinkCreditCardCommand extends AbstractCommand {
    private CreditCardService creditCardService;
    private Validator numberValidator;
    private Validator cvvValidator;
    private Validator thruValidator;

    public PostLinkCreditCardCommand(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
        this.numberValidator = CreditCartNumberValidator.getInstance();
        this.cvvValidator = CreditCardCvvValidator.getInstance();
        this.thruValidator = CreditCardThruValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        CreditCard creditCard = getRequestBody(request, CreditCard.class);
        String locale = getCurrentLocale(request);
        validate(creditCard.getNumber(), locale, numberValidator);
        validate(creditCard.getCvv(), locale, cvvValidator);
        validate(creditCard.getThru(), locale, thruValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            creditCardService.linkCreditCart(creditCard);
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
