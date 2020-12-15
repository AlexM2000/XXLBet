package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;

public interface Validator {
    void validate(Object target, Errors errors, final String locale);
}
