package com.epam.xxlbet.milto.command;

import static com.epam.xxlbet.milto.command.CommandResultType.FORWARD;
import static com.epam.xxlbet.milto.command.CommandResultType.REDIRECT;
import static com.epam.xxlbet.milto.command.CommandResultType.WRITE_DIRECT_TO_RESPONSE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * CommandResult.
 *
 * @author Aliaksei Milto
 */
public class CommandResult {

    private final String page;
    private final CommandResultType commandResultType;
    private final Object responseBody;

    private CommandResult(final String page, final CommandResultType commandResultType, final Object responseBody) {
        this.page = page;
        this.commandResultType = commandResultType;
        this.responseBody = responseBody;
    }

    /**
     * Create redirect command result.
     *
     * @param page Page to which redirect
     * @return redirect command result
     */
    public static CommandResult createRedirectCommandResult(String page) {
        return new CommandResult(page, REDIRECT, null);
    }

    /**
     * Create forward command result.
     *
     * @param page Page to which forward
     * @return forward command result
     */
    public static CommandResult createForwardCommandResult(String page) {
        return new CommandResult(page, FORWARD, null);
    }

    /**
     * Create command result of returning response body.
     *
     * @param responseBody response body to return
     * @return write response body to response command result
     */
    public static CommandResult createWriteDirectlyToResponseCommandResult(final Object responseBody) {
        return new CommandResult(EMPTY, WRITE_DIRECT_TO_RESPONSE, responseBody);
    }

    public String getPage() {
        return page;
    }

    public CommandResultType getCommandResultType() {
        return commandResultType;
    }

    public Object getResponseBody() {
        return responseBody;
    }
}
