package com.epam.xxlbet.milto.command;

import static com.epam.xxlbet.milto.command.CommandType.FORWARD;
import static com.epam.xxlbet.milto.command.CommandType.REDIRECT;
import static com.epam.xxlbet.milto.command.CommandType.WRITE_DIRECT_TO_RESPONSE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * CommandResult.
 *
 * @author Aliaksei Milto
 */
public class CommandResult {

    private final String page;
    private final CommandType commandType;

    private CommandResult(final String page, final CommandType commandType) {
        this.page = page;
        this.commandType = commandType;
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

    public CommandType getCommandType() {
        return commandType;
    }
}
