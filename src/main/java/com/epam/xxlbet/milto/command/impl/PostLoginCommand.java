package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestandresponsebody.LoginRequest;
import com.epam.xxlbet.milto.service.RoleService;
import com.epam.xxlbet.milto.service.StatusService;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.ConfirmationValidator;
import com.epam.xxlbet.milto.validator.impl.UserNotExistsValidator;

import java.io.IOException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * PostLoginCommand.
 *
 * @author Aliaksei Milto
 */
public class PostLoginCommand extends AbstractCommand {
    private UserService userService;
    private RoleService roleService;
    private StatusService statusService;
    private Validator userNotExistsValidator;
    private Validator confirmationValidator;

    public PostLoginCommand(
            final UserService userService, final StatusService statusService, final RoleService roleService
    ) {
        this.userService = userService;
        this.statusService = statusService;
        this.roleService = roleService;
        this.userNotExistsValidator = UserNotExistsValidator.getInstance();
        this.confirmationValidator = ConfirmationValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        LoginRequest loginRequest = getRequestBody(request, LoginRequest.class);
        validate(loginRequest.getLogin(), getCurrentLocale(request), userNotExistsValidator);
        validate(loginRequest.getLogin(), getCurrentLocale(request), confirmationValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            User user = userService.getUserByEmailAndPassword(loginRequest.getLogin(), loginRequest.getPassword());

            if (user != null) {
                request.setSessionAttribute("login", user.getEmail());
                request.setSessionAttribute("user_id", user.getId());
                request.setSessionAttribute("role", roleService.getUserRoleByEmail(user.getEmail()));
                request.setSessionAttribute("status", statusService.getUserStatusByEmail(user.getEmail()));
            } else {
                getErrors().remove(STATUS);
                getErrors().put(STATUS, FAILED);
                getErrors().put("wrong.password", "Wrong password");
            }
        }

        try {
            response.writeJSONValue(getErrors());
            getErrors().clear();
        } catch (final IOException e) {
            getLogger().error("Something went wrong during writing response...", e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
