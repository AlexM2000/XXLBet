package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.requestandresponsebody.CreateBetRequest;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.UserInfoService;
import com.epam.xxlbet.milto.utils.Errors;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostCreateBetCommand.
 *
 * @author Aliaksei Milto
 */
public class PostCreateBetCommand extends AbstractCommand {
    private UserInfoService userInfoService;
    private BetsService betsService;

    public PostCreateBetCommand(final UserInfoService userInfoService, final BetsService betsService) {
        this.userInfoService = userInfoService;
        this.betsService = betsService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        CreateBetRequest createBetRequest = getRequestBody(request, CreateBetRequest.class);
        UserInfo userInfo = userInfoService.getUserInfoByEmail(createBetRequest.getEmail());
        Object responseBody;

        if (userInfo.getBalance().compareTo(createBetRequest.getSum()) < 0) {
            Errors errors = new Errors();
            errors.setLocale(getCurrentLocale(request));
            errors.reject("create-bet-page.bad-balance");
            responseBody = errors.getErrors();
        } else {
            userInfo.setBalance(userInfo.getBalance().subtract(createBetRequest.getSum()));
            userInfoService.updateUserInfo(userInfo);
            betsService.createBet(createBetRequest);
            responseBody = "ok";
        }

        return createWriteDirectlyToResponseCommandResult(responseBody);
    }
}
