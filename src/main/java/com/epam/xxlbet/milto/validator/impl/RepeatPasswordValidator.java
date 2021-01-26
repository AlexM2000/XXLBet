package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.requestandresponsebody.RegistrationRequest;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * RepeatPasswordValidator.
 *
 * @author Aliaksei Milto
 */
public class RepeatPasswordValidator implements Validator {
    private static RepeatPasswordValidator validator;

    private RepeatPasswordValidator() { }

    public static RepeatPasswordValidator getInstance() {
        if (validator == null) {
            validator = new RepeatPasswordValidator();
        }

        return validator;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationRequest requestBody = (RegistrationRequest) target;

        if (!requestBody.getPassword().equals(requestBody.getRepeatPassword())) {
            errors.reject("passwords.not.match");
        }
    }
}
