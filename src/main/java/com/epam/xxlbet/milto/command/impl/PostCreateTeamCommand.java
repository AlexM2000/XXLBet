package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.CreateTeamRequest;
import com.epam.xxlbet.milto.service.OpponentsService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.OpponentExistsValidator;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

public class PostCreateTeamCommand extends AbstractCommand {
    private OpponentsService opponentsService;
    private Validator opponentValidator;

    public PostCreateTeamCommand(OpponentsService opponentsService) {
        this.opponentsService = opponentsService;
        this.opponentValidator = OpponentExistsValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        CreateTeamRequest teamRequest = getRequestBody(request, CreateTeamRequest.class);
        validate(teamRequest.getOpponentName(), getCurrentLocale(request), opponentValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            opponentsService.createOpponent(teamRequest);
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
