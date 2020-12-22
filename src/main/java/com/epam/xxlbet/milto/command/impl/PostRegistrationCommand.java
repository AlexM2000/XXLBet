package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.EmailValidator;
import com.epam.xxlbet.milto.validator.impl.PasswordValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberExistsValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberValidator;
import com.epam.xxlbet.milto.validator.impl.UserExistsValidator;

import java.io.IOException;

public class PostRegistrationCommand extends AbstractCommand {
    private Validator userExistsValidater;
    private Validator phoneNumberExistsValidator;
    private Validator passwordValidator;
    private Validator phoneNumberValidator;
    private Validator emailValidator;
    private UserService userService;

    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
        this.emailValidator = EmailValidator.getInstance();
        this.passwordValidator = PasswordValidator.getInstance();
        this.phoneNumberValidator = PhoneNumberValidator.getInstance();
        this.userExistsValidater = UserExistsValidator.getInstance();
        this.phoneNumberExistsValidator = PhoneNumberExistsValidator.getInstance();
    }


    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        RegistrationRequest body = getRequestBody(request, RegistrationRequest.class);
        validate(body.getEmail(), getCurrentLocale(request), emailValidator);
        validate(body.getPassword(), getCurrentLocale(request), passwordValidator);
        validate(body.getPhoneNumber(), getCurrentLocale(request), phoneNumberValidator);
        validate(body.getEmail(), getCurrentLocale(request), userExistsValidater);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            userService.createNewUser(body);
        }

        try {
            response.writeJSONValue(getErrors());
            getErrors().clear();
        } catch (final IOException e) {
            getLogger().error("Something went wrong during writing response...", e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
