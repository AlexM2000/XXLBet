package com.epam.xxlbet.milto.command;

import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;

/**
 * Command.
 *
 * @author Aliaksei Milto
 */
public interface Command {

    /**
     * Execute command.
     *
     * @param request {@link RequestContext}
     * @param response {@link ResponseContext}
     * @return {@link CommandResult}
     */
    CommandResult execute(RequestContext request, ResponseContext response);

}
