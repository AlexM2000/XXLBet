package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * CreditCartNumberValidator.
 *
 * @author Aliaksei Milto
 */
public class CreditCartNumberValidator implements Validator {
    private static CreditCartNumberValidator instance;
    private static final String PATTERN = "\\d{16}";

    private CreditCartNumberValidator() { }

    public static CreditCartNumberValidator getInstance() {
        if (instance == null) {
            instance = new CreditCartNumberValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String number = (String) target;

        if (!number.matches(PATTERN)) {
            errors.reject("credit.card.number.invalid");
        }
    }
}
