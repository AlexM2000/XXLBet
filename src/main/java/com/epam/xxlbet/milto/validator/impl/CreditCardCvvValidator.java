package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * CreditCardCvvValidator.
 *
 * @author Aliaksei Milto
 */
public class CreditCardCvvValidator implements Validator {
    private static CreditCardCvvValidator instance;
    private static final String PATTERN = "^\\d{3}$";

    private CreditCardCvvValidator() { }

    public static CreditCardCvvValidator getInstance() {
        if (instance == null) {
            instance = new CreditCardCvvValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String cvv = (String) target;

        if (!cvv.matches(PATTERN)) {
            errors.reject("credit.card.cvv.invalid");
        }
    }
}
