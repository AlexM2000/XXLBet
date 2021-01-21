package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.ChangePasswordRequest;
import com.epam.xxlbet.milto.service.PasswordChangeRequestService;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostChangePasswordCommand.
 *
 * @author Aliaksei Milto
 */
public class PostChangePasswordCommand extends AbstractCommand {
    private PasswordChangeRequestService service;

    public PostChangePasswordCommand(PasswordChangeRequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        service.changePassword(getRequestBody(request, ChangePasswordRequest.class));
        return createWriteDirectlyToResponseCommandResult("ok");
    }
}
