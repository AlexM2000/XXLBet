package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.UserInfoService;

public class GetProfileCommand implements Command {
    private static final String PROFILE_PAGE = "/views/profile.jsp";
    private UserInfoService userInfoService;
    private BetsService betsService;

    public GetProfileCommand(final UserInfoService userInfoService, final BetsService betsService) {
        this.userInfoService = userInfoService;
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        UserInfo userInfo = userInfoService.getUserInfoByEmail((String) request.getSessionAttribute("login"));

        request.setAttribute("bets", betsService.getBetsHistoryByUser(userInfo.getEmail(), userInfo.getPhoneNumber()));
        request.setAttribute("user", userInfo);

        return CommandResult.createForwardCommandResult(PROFILE_PAGE);
    }
}
