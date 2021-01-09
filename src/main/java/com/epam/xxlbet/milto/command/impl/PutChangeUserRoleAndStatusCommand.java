package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestandresponsebody.ChangeUserRoleAndStatusRequest;
import com.epam.xxlbet.milto.service.RoleService;
import com.epam.xxlbet.milto.service.StatusService;
import com.epam.xxlbet.milto.service.UserInfoService;

import static com.epam.xxlbet.milto.command.CommandResult.createRedirectCommandResult;

public class PutChangeUserRoleAndStatusCommand extends AbstractCommand {
    private static final String BOOKMAKER_PAGE = "/views/bookmaker-page.jsp";
    private UserInfoService userInfoService;
    private RoleService roleService;
    private StatusService statusService;

    public PutChangeUserRoleAndStatusCommand(
            final UserInfoService userInfoService, final RoleService roleService, final StatusService statusService
    ) {
        this.userInfoService = userInfoService;
        this.roleService = roleService;
        this.statusService = statusService;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        ChangeUserRoleAndStatusRequest body = getRequestBody(request, ChangeUserRoleAndStatusRequest.class);

        UserInfo userInfo = userInfoService.getUserInfoByEmail(body.getEmail());
        userInfo.setRoleId(roleService.getRoleByName(body.getRole()).getId());
        userInfo.setStatusId(statusService.getUserStatusByName(body.getStatus()).getId());
        userInfoService.updateUserInfo(userInfo);

        return createRedirectCommandResult(BOOKMAKER_PAGE);
    }
}
