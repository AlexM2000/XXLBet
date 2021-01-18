package com.epam.xxlbet.milto.validator.impl;

import com.epam.xxlbet.milto.service.TournamentService;
import com.epam.xxlbet.milto.service.impl.TournamentServiceImpl;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;

/**
 * TournamentExistsValidator.
 *
 * @author Aliaksei Milto
 */
public class TournamentExistsValidator implements Validator {
    private static TournamentExistsValidator instance;
    private TournamentService tournamentService;

    private TournamentExistsValidator() {
        this.tournamentService = TournamentServiceImpl.getInstance();
    }

    public static TournamentExistsValidator getInstance() {
        if (instance == null) {
            instance = new TournamentExistsValidator();
        }

        return instance;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String tournamentName = (String) target;

        if (tournamentService.getTournamentByTournamentName(tournamentName) != null) {
            errors.reject("tournament.already.exists", tournamentName);
        }
    }
}
