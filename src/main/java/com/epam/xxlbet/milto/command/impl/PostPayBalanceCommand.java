package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.requestandresponsebody.PayBalanceRequest;
import com.epam.xxlbet.milto.service.UserInfoService;
import com.epam.xxlbet.milto.service.UserService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostPayBalanceCommand.
 *
 * @author Aliaksei Milto
 */
public class PostPayBalanceCommand extends AbstractCommand {
    private UserService userService;
    private UserInfoService userInfoService;

    public PostPayBalanceCommand(UserService userService, UserInfoService userInfoService) {
        this.userService = userService;
        this.userInfoService = userInfoService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        PayBalanceRequest payBalanceRequest = getRequestBody(request, PayBalanceRequest.class);

        UserInfo userInfo = userInfoService.getUserInfoByEmail(
                userService.getUserById(payBalanceRequest.getUserId()).getEmail()
        );
        userInfo.setBalance(userInfo.getBalance().add(payBalanceRequest.getMoney()));

        userInfoService.updateUserInfo(userInfo);

        return createWriteDirectlyToResponseCommandResult("ok");
    }
}
