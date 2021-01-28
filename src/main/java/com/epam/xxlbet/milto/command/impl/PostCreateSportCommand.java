package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.service.SportService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.SportExistsValidator;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;
import static java.util.Collections.singletonMap;

/**
 * PostCreateSportCommand.
 *
 * @author Aliaksei Milto
 */
public class PostCreateSportCommand extends AbstractCommand {
    private SportService sportService;
    private Validator sportExistsValidator;

    public PostCreateSportCommand(SportService sportService) {
        this.sportService = sportService;
        this.sportExistsValidator = SportExistsValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass() + " " + request.getContentType());

        String sportName = getRequestBody(request, String.class);
        validate(sportName, getCurrentLocale(request), sportExistsValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            sportService.createSport(sportName);
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
