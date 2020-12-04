package xxl.bet.milto.validator.impl;

import xxl.bet.milto.utils.Errors;
import xxl.bet.milto.utils.PropertyLoader;
import xxl.bet.milto.validator.Validator;

import static xxl.bet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;

/**
 * PhoneNumberValidator.
 *
 * @author alexm2000
 */
public class PhoneNumberValidator implements Validator {
    private static final String PHONENUMBER_REGEXP_ID = "xxl.bet.milto.phonenumber.regexp";
    private static final String ERROR_MSG_ID = "phonenumber.not.matches.regexp";
    private static final String PATTERN = PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, PHONENUMBER_REGEXP_ID)
            .orElse("^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\W_\\\\d])).{7,20}$");

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String phoneNumber = (String) target;

        if (!phoneNumber.matches(PATTERN)) {
            errors.reject(ERROR_MSG_ID, locale);
        }
    }
}
