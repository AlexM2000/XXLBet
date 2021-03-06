package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestandresponsebody.LoginRequest;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * CorrectPasswordValidator.
 *
 * @author Aliaksei Milto
 */
public class PasswordLoginValidator implements Validator {
    private static PasswordLoginValidator instance;
    private UserService userService;

    private PasswordLoginValidator() {
        this.userService = UserServiceImpl.getInstance();
    }

    public static PasswordLoginValidator getInstance() {
        if (instance == null) {
            instance = new PasswordLoginValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequest loginRequest = (LoginRequest) target;
        User userByEmail = userService.getUserByEmail(loginRequest.getLogin());
        User userByEmailAndPassword = userService.getUserByEmailAndPassword(
                loginRequest.getLogin(), loginRequest.getPassword()
        );

        if (userByEmail != null && userByEmailAndPassword == null) {
            errors.reject("wrong.password");
        }
    }
}
