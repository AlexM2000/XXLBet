package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;

/**
 * Validates target. If target didn't pass validation, adds error code to Errors with given locale.
 *
 * @author Aliaksei Milto
 */
public interface Validator {

    /**
     * Validate the supplied target object.
     * The supplied {@link Errors} instance can be used to report any resulting validation errors.
     *
     * @param target the object that is to be validated
     * @param errors contextual state about the validation process
     */
    void validate(Object target, Errors errors);
}
