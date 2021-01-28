package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.ChangePasswordRequest;
import com.epam.xxlbet.milto.service.PasswordChangeRequestService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.PasswordFormatValidator;
import com.epam.xxlbet.milto.validator.impl.RepeatPasswordValidator;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostChangePasswordCommand.
 *
 * @author Aliaksei Milto
 */
public class PostChangePasswordCommand extends AbstractCommand {
    private PasswordChangeRequestService service;
    private Validator passwordFormatValidator;

    public PostChangePasswordCommand(PasswordChangeRequestService service) {
        this.service = service;
        this.passwordFormatValidator = PasswordFormatValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        ChangePasswordRequest passwordRequest = getRequestBody(request, ChangePasswordRequest.class);
        validate(passwordRequest.getPassword(), getCurrentLocale(request), passwordFormatValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            service.changePassword(passwordRequest);
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
