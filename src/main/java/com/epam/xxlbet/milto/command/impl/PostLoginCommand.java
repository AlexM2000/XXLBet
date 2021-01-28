package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.requestandresponsebody.LoginRequest;
import com.epam.xxlbet.milto.service.RoleService;
import com.epam.xxlbet.milto.service.StatusService;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.ConfirmationValidator;
import com.epam.xxlbet.milto.validator.impl.PasswordLoginValidator;
import com.epam.xxlbet.milto.validator.impl.UserNotExistsValidator;

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
    private Validator correctPasswordValidator;

    public PostLoginCommand(
            final UserService userService, final StatusService statusService, final RoleService roleService
    ) {
        this.userService = userService;
        this.statusService = statusService;
        this.roleService = roleService;
        this.userNotExistsValidator = UserNotExistsValidator.getInstance();
        this.confirmationValidator = ConfirmationValidator.getInstance();
        this.correctPasswordValidator = PasswordLoginValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        LoginRequest loginRequest = getRequestBody(request, LoginRequest.class);
        String locale = getCurrentLocale(request);
        validate(loginRequest.getLogin(), locale, userNotExistsValidator);
        validate(loginRequest.getLogin(), locale, confirmationValidator);
        validate(loginRequest, locale, correctPasswordValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            User user = userService.getUserByEmailAndPassword(
                    loginRequest.getLogin(), loginRequest.getPassword()
            );

            request.setSessionAttribute("login", user.getEmail());
            request.setSessionAttribute("user_id", user.getId());
            request.setSessionAttribute("role", roleService.getUserRoleByEmail(user.getEmail()));
            request.setSessionAttribute("status", statusService.getUserStatusByEmail(user.getEmail()));
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }
}
