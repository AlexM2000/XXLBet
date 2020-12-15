package com.emap.xxlbet.milto.validator.impl;

import com.emap.xxlbet.milto.service.UserService;
import com.emap.xxlbet.milto.service.impl.XxlUserServiceImpl;
import com.emap.xxlbet.milto.utils.Errors;
import com.emap.xxlbet.milto.validator.Validator;

public class UserNotExistsValidator implements Validator {
    private static UserNotExistsValidator instance;
    private UserService service;

    private UserNotExistsValidator() {
        service = XxlUserServiceImpl.getInstance();
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
