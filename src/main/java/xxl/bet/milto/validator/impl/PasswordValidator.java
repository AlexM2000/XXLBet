package xxl.bet.milto.validator.impl;

import xxl.bet.milto.utils.Errors;
import xxl.bet.milto.utils.PropertyLoader;
import xxl.bet.milto.validator.Validator;

import static xxl.bet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;

/**
 * PasswordValidator.
 *
 * @author alexm2000
 */
public final class PasswordValidator implements Validator {
    private static final String PASSWORD_PATTERN_ID = "xxl.bet.milto.password.regexp";
    private static final String PATTERN = PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, PASSWORD_PATTERN_ID)
            .orElse("^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\W_\\\\d])).{7,20}$");
    private static PasswordValidator instance;

    private PasswordValidator() { }

    public static PasswordValidator getInstance() {
        if (instance == null) {
            instance = new PasswordValidator();
        }

        return instance;
    }

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String password = (String) target;

        if (!password.matches(PATTERN)) {
            errors.reject("password.not.matches.regexp", locale);
        }
    }
}
