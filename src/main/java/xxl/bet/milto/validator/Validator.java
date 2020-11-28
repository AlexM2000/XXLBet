package xxl.bet.milto.validator;

import xxl.bet.milto.domain.Errors;

public interface Validator {
    void validate(Object target, Errors errors, final String locale);
}
