package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.requestandresponsebody.CreateMatchRequest;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * EqualOpponentsValidator.
 *
 * @author Aliaksei Milto
 */
public class EqualOpponentsValidator implements Validator {
    private static EqualOpponentsValidator instance;

    private EqualOpponentsValidator() { }

    public static EqualOpponentsValidator getInstance() {
        if (instance == null) {
            instance = new EqualOpponentsValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateMatchRequest request = (CreateMatchRequest) target;

        if (request.getTeam1().equals(request.getTeam2())) {
            errors.reject("cannot.create.with.equal.opponents");
        }
    }
}
