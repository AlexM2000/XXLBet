package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.CreditCardService;
import com.epam.xxlbet.milto.service.UserInfoService;
import com.epam.xxlbet.milto.service.UserService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetProfileCommand.
 *
 * @author Aliaksei Milto
 */
public class GetProfileCommand extends AbstractCommand {
    private static final String PROFILE_PAGE = "/profile";
    private UserInfoService userInfoService;
    private UserService userService;
    private BetsService betsService;
    private CreditCardService creditCardService;

    public GetProfileCommand(
            final UserInfoService userInfoService,
            final BetsService betsService,
            final CreditCardService creditCardService,
            final UserService userService
    ) {
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.betsService = betsService;
        this.creditCardService = creditCardService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        UserInfo userInfo = userInfoService.getUserInfoByEmail((String) request.getSessionAttribute("login"));
        request.setAttribute("bets", betsService.getBetsHistoryByUser(userInfo.getEmail()));
        request.setAttribute("cards",
                creditCardService.getCreditCardsByUserId(userService.getUserByEmail(userInfo.getEmail()).getId())
        );
        request.setAttribute("userInfo", userInfo);

        return createForwardCommandResult(PROFILE_PAGE);
    }
}
