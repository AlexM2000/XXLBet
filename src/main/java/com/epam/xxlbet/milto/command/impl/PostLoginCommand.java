package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestbody.LoginRequest;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.ConfirmationValidator;
import com.epam.xxlbet.milto.validator.impl.UserNotExistsValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostLoginCommand extends AbstractCommand {
    private UserService userService;
    private Validator userNotExistsValidator;
    private Validator confirmationValidator;

    public PostLoginCommand(UserService userService) {
        this.userService = userService;
        this.userNotExistsValidator = UserNotExistsValidator.getInstance();
        this.confirmationValidator = ConfirmationValidator.getInstance();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LoginRequest loginRequest = getRequestBody(request, LoginRequest.class);
        validate(loginRequest.getLogin(), getCurrentLocale(request), userNotExistsValidator);
        validate(loginRequest.getLogin(), getCurrentLocale(request), confirmationValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            User user = userService.getUserByEmailAndPassword(loginRequest.getLogin(), loginRequest.getPassword());
            if (user != null) {
                request.getSession().setAttribute("login", user.getEmail());
                request.getSession().setAttribute("user_id", user.getId());
            } else {
                getErrors().remove(STATUS);
                getErrors().put(STATUS, FAILED);
                getErrors().put("wrong.password", "Wrong password");
            }
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
