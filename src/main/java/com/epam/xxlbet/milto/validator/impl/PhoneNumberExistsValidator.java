package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * PhoneNumberExistsValidator.
 *
 * @author Aliaksei Milto
 */
public class PhoneNumberExistsValidator implements Validator {
    private static PhoneNumberExistsValidator instance;
    private UserService userService;

    private PhoneNumberExistsValidator() {
        userService = UserServiceImpl.getInstance();
    }

    public static PhoneNumberExistsValidator getInstance() {
        if (instance == null) {
            instance = new PhoneNumberExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String phoneNumber = (String) target;

        if (userService.isPhoneNumberExists(phoneNumber)) {
            errors.reject("user.with.phonenumber.exists", phoneNumber);
        }
    }
}
