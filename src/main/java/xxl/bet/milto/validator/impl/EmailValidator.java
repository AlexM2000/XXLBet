package xxl.bet.milto.validator.impl;

import xxl.bet.milto.domain.Errors;
import xxl.bet.milto.utils.PropertyLoader;
import xxl.bet.milto.validator.Validator;

import static xxl.bet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;

/**
 * EmailValidator.
 *
 * @author alexm2000
 */
public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN_ID = "xxl.bet.milto.email.regexp";
    private static final String ERROR_MSG_ID = "email.not.matches.regexp";
    private static final String EMAIL_PATTERN = PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, EMAIL_PATTERN_ID)
            .orElse("^\\\\s*?(.+)@(.+?)\\\\s*$");

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String email = (String) target;

        if (email.endsWith(".") || !email.matches(EMAIL_PATTERN)) {
            errors.reject(ERROR_MSG_ID, locale);
        }
    }
}
