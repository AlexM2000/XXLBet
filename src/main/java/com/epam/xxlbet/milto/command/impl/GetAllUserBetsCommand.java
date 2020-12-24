package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.BetsService;

import java.io.IOException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

public class GetAllUserBetsCommand implements Command {
    private static final String PROFILE_PAGE = "/views/profile.jsp";
    private BetsService betsService;

    public GetAllUserBetsCommand(BetsService betsService) {
        this.betsService = betsService;
    }


    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        try {
            response.writeJSONValue(betsService.getBetsHistoryByUser((String) request.getSessionAttribute("login")));
        } catch (IOException e) {
            throw new ServiceException(e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
