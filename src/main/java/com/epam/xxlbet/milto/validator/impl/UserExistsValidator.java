package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * UserExistsValidator.
 *
 * @author Aliaksei Milto
 */
public final class UserExistsValidator implements Validator {
    private static UserExistsValidator instance;
    private UserService service;

    private UserExistsValidator() {
        service = UserServiceImpl.getInstance();
    }

    public static UserExistsValidator getInstance() {
        if (instance == null) {
            instance = new UserExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String email = (String) target;

        if (service.getUserByEmail(email) != null) {
            errors.reject("user.email.already.exists", locale, email);
        }
    }
}
