package com.emap.xxlbet.milto.validator.impl;

import com.emap.xxlbet.milto.service.UserService;
import com.emap.xxlbet.milto.service.impl.XxlUserServiceImpl;
import com.emap.xxlbet.milto.utils.Errors;
import com.emap.xxlbet.milto.validator.Validator;

/**
 * UserExistsValidator.
 *
 * @author alexm2000
 */
public final class UserExistsValidator implements Validator {
    private static UserExistsValidator instance;
    private UserService service;

    private UserExistsValidator() {
        service = XxlUserServiceImpl.getInstance();
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

        if (service.checkIfUserExists(email)) {
            errors.reject("user.email.already.exists", locale, email);
        }
    }
}
