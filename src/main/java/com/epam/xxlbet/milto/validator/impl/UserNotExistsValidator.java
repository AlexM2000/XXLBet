package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * UserNotExistsValidator.
 *
 * @author Aliaksei Milto
 */
public class UserNotExistsValidator implements Validator {
    private static UserNotExistsValidator instance;
    private UserService service;

    private UserNotExistsValidator() {
        service = UserServiceImpl.getInstance();
    }

    public static UserNotExistsValidator getInstance() {
        if (instance == null) {
            instance = new UserNotExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors, String locale) {
        String email = (String) target;

        if (!service.checkIfUserExists(email)) {
            errors.reject("user.email.not.exists", locale, email);
        }
    }
}
