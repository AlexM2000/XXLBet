package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.EmailValidator;
import com.epam.xxlbet.milto.validator.impl.PasswordValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberValidator;
import com.epam.xxlbet.milto.validator.impl.UserExistsValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostRegistrationCommand extends AbstractCommand {
    private Validator userExistsValidater;
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
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getParameterMap().forEach(
                (key, value) -> getLogger().debug("{} {}", key, value)
        );
        getLogger().debug(request.getParameter("body"));
        getLogger().debug(request.getParameter("body[password]"));
        getLogger().debug(request.getParameter("password"));
        RegistrationRequest body = getRequestBody(request, RegistrationRequest.class);
        validate(body.getPassword(), getCurrentLocale(request), passwordValidator);
        validate(body.getPhoneNumber(), getCurrentLocale(request), phoneNumberValidator);
        validate(body.getEmail(), getCurrentLocale(request), userExistsValidater);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            userService.createNewUser(body);
        }

        try {
            getMapper().writeValue(response.getWriter(), getErrors());
            getErrors().clear();
        } catch (final IOException e) {
            getLogger().error("Something went wrong during writing response...", e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
