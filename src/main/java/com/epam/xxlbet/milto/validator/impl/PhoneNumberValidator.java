package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * PhoneNumberValidator.
 *
 * @author alexm2000
 */
public final class PhoneNumberValidator implements Validator {
    private static final String PHONENUMBER_REGEXP_ID = "xxl.bet.milto.phonenumber.regexp";
    private static final String ERROR_MSG_ID = "phonenumber.not.matches.regexp";
    private static final String PATTERN = PropertyLoader.getInstance().getStringProperty(XxlBetConstants.PROJECT_PROPERTIES, PHONENUMBER_REGEXP_ID)
            .orElse("^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\W_\\\\d])).{7,20}$");
    private static PhoneNumberValidator instance;

    private PhoneNumberValidator() { }

    public static PhoneNumberValidator getInstance() {
        if (instance == null) {
            instance = new PhoneNumberValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String phoneNumber = (String) target;

        if (!phoneNumber.matches(PATTERN)) {
            errors.reject(ERROR_MSG_ID, locale);
        }
    }
}