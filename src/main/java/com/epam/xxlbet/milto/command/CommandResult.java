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

    private CommandResult(final String page, final CommandResultType commandResultType) {
        this.page = page;
        this.commandResultType = commandResultType;
    }

    public static CommandResult createRedirectCommandResult(String page) {
        return new CommandResult(page, REDIRECT);
    }

    public static CommandResult createForwardCommandResult(String page) {
        return new CommandResult(page, FORWARD);
    }

    public static CommandResult createWriteDirectlyToResponseCommandResult() {
        return new CommandResult(EMPTY, WRITE_DIRECT_TO_RESPONSE);
    }

    public String getPage() {
        return page;
    }

    public CommandResultType getCommandResultType() {
        return commandResultType;
    }
}
