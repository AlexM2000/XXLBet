package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.SportService;
import com.epam.xxlbet.milto.service.impl.SportServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * SportExistsValidator.
 *
 * @author Aliaksei Milto
 */
public class SportExistsValidator implements Validator {
    private static SportExistsValidator instance;
    private SportService sportService;

    private SportExistsValidator() {
        this.sportService = SportServiceImpl.getInstance();
    }

    public static SportExistsValidator getInstance() {
        if (instance == null) {
            instance = new SportExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String sport = (String) target;

        if (sportService.getSportByName(sport) != null) {
            errors.reject("sport.already.exists", sport);
        }
    }
}
