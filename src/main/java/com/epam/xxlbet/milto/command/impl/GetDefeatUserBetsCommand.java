package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.BetsService;

import java.io.IOException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

public class GetDefeatUserBetsCommand implements Command {
    private BetsService betsService;

    public GetDefeatUserBetsCommand(final BetsService betsService) {
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        try {
            response.writeJSONValue(betsService.getDefeatBetsByUser((String) request.getSessionAttribute("login")));
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
