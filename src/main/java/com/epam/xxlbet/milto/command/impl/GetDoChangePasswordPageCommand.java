package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.service.PasswordChangeRequestService;

import static com.epam.xxlbet.milto.command.CommandResult.createForwardCommandResult;

/**
 * GetDoChangePasswordPageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetDoChangePasswordPageCommand extends AbstractCommand {
    private static final String PAGE = "/do-change-password";
    private PasswordChangeRequestService service;

    public GetDoChangePasswordPageCommand(PasswordChangeRequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        PasswordChangeRequest passwordRequest = service.findPasswordChangeRequestByToken(request.getParameter("token"));

        if (passwordRequest != null) {
            request.setAttribute("passwordRequest", passwordRequest);
            return createForwardCommandResult(PAGE);
        } else {
            return createForwardCommandResult("/expired");
        }
    }
}
