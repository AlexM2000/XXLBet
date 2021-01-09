package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.UserInfoService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetProfileCommand.
 *
 * @author Aliaksei Milto
 */
public class GetProfileCommand extends AbstractCommand {
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

        request.setAttribute("bets", betsService.getBetsHistoryByUser(userInfo.getEmail()));
        request.setAttribute("userInfo", userInfo);

        return createForwardCommandResult(PROFILE_PAGE);
    }
}
