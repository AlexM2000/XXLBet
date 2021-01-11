package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.BetsService;

import java.io.IOException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 *  GetAllUserBetsCommand.
 *
 * @author Aliaksei Milto
 */
public class GetAllUserBetsCommand extends AbstractCommand {
    private BetsService betsService;

    public GetAllUserBetsCommand(BetsService betsService) {
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());
        try {
            response.writeJSONValue(betsService.getBetsHistoryByUser((String) request.getSessionAttribute("login")));
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
