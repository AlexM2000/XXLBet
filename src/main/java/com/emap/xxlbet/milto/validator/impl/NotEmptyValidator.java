package com.emap.xxlbet.milto.validator.impl;

import com.emap.xxlbet.milto.utils.Errors;
import com.emap.xxlbet.milto.validator.Validator;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class NotEmptyValidator implements Validator {
    @Override
    public void validate(Object target, Errors errors, String locale) {
        String field = (String) target;

        if (isBlank(field)) {
            errors.reject(field, locale);
        }
    }
}
