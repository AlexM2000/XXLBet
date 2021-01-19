package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CreditCardThruValidator .
 *
 * @author Aliaksei Milto
 */
public class CreditCardThruValidator implements Validator {
    private static CreditCardThruValidator instance;
    private static final String PATTERN = "(0[1-9]|1[0-2])/\\d{2}";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/yy");

    private CreditCardThruValidator() { }

    public static CreditCardThruValidator getInstance() {
        if (instance == null) {
            instance = new CreditCardThruValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String thru = (String) target;

        if (!thru.matches(PATTERN)) {
            errors.reject("credit.card.thru.invalid");
            return;
        }

        try {
            Date thruDate = DATE_FORMAT.parse(thru);
            if (thruDate.before(new Date())) {
                errors.reject("credit.card.thru.invalid");
            }
        } catch (ParseException e) {
            errors.reject("credit.card.thru.invalid");
        }

    }
}
