package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.validator.Validator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;

/**
 * PhoneNumberValidator.
 *
 * @author Aliaksei Milto
 */
public final class PhoneNumberValidator implements Validator {
    private static final String PHONENUMBER_REGEXP_ID = "xxl.bet.milto.phonenumber.regexp";
    private static final String ERROR_MSG_ID = "phonenumber.not.matches.regexp";
    private static final String PATTERN = PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, PHONENUMBER_REGEXP_ID)
            .orElse("^(80|375)(25|29|33|44)(\\\\d{7})$");
    private static PhoneNumberValidator instance;

    private PhoneNumberValidator() { }

    public static PhoneNumberValidator getInstance() {
        if (instance == null) {
            instance = new PhoneNumberValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        String phoneNumber = (String) target;

        if (!phoneNumber.matches(PATTERN)) {
            errors.reject(ERROR_MSG_ID);
        }
    }
}
