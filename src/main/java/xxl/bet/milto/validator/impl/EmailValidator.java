package xxl.bet.milto.validator.impl;

import xxl.bet.milto.domain.Errors;
import xxl.bet.milto.utils.ProjectProperties;
import xxl.bet.milto.validator.Validator;

public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN = ProjectProperties.getInstance().getStringProperty("xxl.bet.milto.email.regexp")
            .orElse("^\\\\s*?(.+)@(.+?)\\\\s*$");

    @Override
    public void validate(Object target, Errors errors, String locale) {
        String email = (String) target;

        if (email.endsWith(".") || !email.matches(EMAIL_PATTERN)) {
            errors.reject("email.not.matches.regexp", locale);
        }
    }
}
