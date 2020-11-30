package xxl.bet.milto.validator.impl;

import xxl.bet.milto.domain.Errors;
import xxl.bet.milto.utils.ProjectProperties;
import xxl.bet.milto.validator.Validator;

public class PasswordValidator implements Validator {
    private String pattern = ProjectProperties.getInstance().getStringProperty("xxl.bet.milto.password.regexp")
            .orElse("^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\W_\\\\d])).{7,20}$");

    @Override
    public void validate(final Object target, final Errors errors, final String locale) {
        String password = (String) target;

        if (!password.matches(pattern)) {
            errors.reject("password.not.matches.regexp", locale);
        }
    }
}
