package com.emap.xxlbet.milto.validator.impl;

import com.emap.xxlbet.milto.utils.PropertyLoader;
import com.emap.xxlbet.milto.utils.Errors;
import com.emap.xxlbet.milto.validator.Validator;

import static com.emap.xxlbet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;

/**
 * EmailValidator.
 *
 * @author alexm2000
 */
public final class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN_ID = "xxl.bet.milto.email.regexp";
    private static final String ERROR_MSG_ID = "email.not.matches.regexp";
    private static final String EMAIL_PATTERN = PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, EMAIL_PATTERN_ID)
            .orElse("^\\\\s*?(.+)@(.+?)\\\\s*$");
    private static EmailValidator instance;

    private EmailValidator() { }

    public static EmailValidator getInstance() {
        if (instance == null) {
            instance = new EmailValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String email = (String) target;

        if (email.endsWith(".") || !email.matches(EMAIL_PATTERN)) {
            errors.reject(ERROR_MSG_ID, locale);
        }
    }
}
