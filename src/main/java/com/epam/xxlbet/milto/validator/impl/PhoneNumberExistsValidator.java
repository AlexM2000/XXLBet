package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.XxlUserServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

public class PhoneNumberExistsValidator implements Validator {
    private static PhoneNumberExistsValidator instance;
    private UserService userService;

    private PhoneNumberExistsValidator() {
        userService = XxlUserServiceImpl.getInstance();
    }

    public static PhoneNumberExistsValidator getInstance() {
        return instance;
    }

    @Override
    public void validate(Object target, Errors errors, String locale) {
        String phoneNumber = (String) target;

        if (userService.checkIfUserExistsByPhoneNumber(phoneNumber)) {
            errors.reject("user.with.phonenumber.exists", locale, phoneNumber);
        }
    }
}
