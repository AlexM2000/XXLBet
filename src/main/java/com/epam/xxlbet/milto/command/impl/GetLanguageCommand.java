package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * GetLanguageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetLanguageCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(GetLanguageCommand.class);

    @Override
    public CommandResult execute(final RequestContext request, final ResponseContext response) throws ServiceException {
        try {
            String locale = request.getParameter("lang") != null ? request.getParameter("lang") : "en";
            response.addCookie(new Cookie("language", locale));
            response.writeValue("ok");
        } catch (final IOException e) {
            LOG.error("Something went wrong during changing language", e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
