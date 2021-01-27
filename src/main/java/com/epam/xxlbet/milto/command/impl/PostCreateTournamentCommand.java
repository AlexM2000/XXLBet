package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.CreateTournamentRequest;
import com.epam.xxlbet.milto.service.TournamentService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.TournamentExistsValidator;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostCreateTournamentCommand.
 *
 * @author Aliaksei Milto
 */
public class PostCreateTournamentCommand extends AbstractCommand {
    private TournamentService tournamentService;
    private Validator tournamentValidator;

    public PostCreateTournamentCommand(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
        this.tournamentValidator = TournamentExistsValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        CreateTournamentRequest tournamentRequest = getRequestBody(request, CreateTournamentRequest.class);
        validate(tournamentRequest.getTournamentName(), getCurrentLocale(request), tournamentValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            tournamentService.createTournament(tournamentRequest);
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
