package com.emap.xxlbet.milto.validator;

import com.emap.xxlbet.milto.utils.Errors;

public interface Validator {
    void validate(Object target, Errors errors, final String locale);
}
