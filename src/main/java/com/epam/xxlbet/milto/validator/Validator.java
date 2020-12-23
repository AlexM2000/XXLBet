package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;

/**
 * Validates target. If target didn't pass validation, adds error code to Errors with given locale.
 *
 * @author Aliaksei Milto
 */
public interface Validator {
    void validate(Object target, Errors errors, final String locale);
}
