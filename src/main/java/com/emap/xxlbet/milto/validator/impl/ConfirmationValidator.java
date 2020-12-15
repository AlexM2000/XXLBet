package com.emap.xxlbet.milto.validator.impl;

import com.emap.xxlbet.milto.service.UserService;
import com.emap.xxlbet.milto.domain.User;
import com.emap.xxlbet.milto.service.impl.XxlUserServiceImpl;
import com.emap.xxlbet.milto.utils.Errors;
import com.emap.xxlbet.milto.validator.Validator;

public class ConfirmationValidator implements Validator {
    private static ConfirmationValidator instance;
    private UserService service;

    private ConfirmationValidator() {
        this.service = XxlUserServiceImpl.getInstance();
    }

    public static ConfirmationValidator getInstance() {
        if (instance == null) {
            instance = new ConfirmationValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String email = (String) target;

        User user = service.getUserByEmail(email);

        if (!user.getEnabled()) {
            errors.reject("user.please.confirm.registration", locale);
        }
    }
}
