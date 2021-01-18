package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.OpponentsService;
import com.epam.xxlbet.milto.service.impl.OpponentsServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * TeamExistsValidator.
 *
 * @author Aliaksei Milto
 */
public class OpponentExistsValidator implements Validator {
    private static OpponentExistsValidator instance;
    private OpponentsService opponentsService;

    private OpponentExistsValidator() {
        this.opponentsService = OpponentsServiceImpl.getInstance();
    }

    public static OpponentExistsValidator getInstance() {
        if (instance == null) {
            instance = new OpponentExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String opponentName = (String) target;

        if (opponentsService.getOpponentByName(opponentName) != null) {
            errors.reject("team.already.exists", opponentName);
        }
    }
}
