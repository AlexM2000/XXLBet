package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.io.IOException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;

/**
 * GetLanguageCommand.
 *
 * @author Aliaksei Milto
 */
public class GetLanguageCommand extends AbstractCommand {
    private static final Logger LOG = LoggerFactory.getLogger(GetLanguageCommand.class);

    @Override
    public CommandResult execute(final RequestContext request, final ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());
        try {
            String locale = request.getParameter("lang") != null ? request.getParameter("lang") : "en";
            response.addCookie(new Cookie("language", locale));
            response.writeValue("ok");
        } catch (final IOException e) {
            LOG.error("Something went wrong during changing language", e);
        }

        return createWriteDirectlyToResponseCommandResult();
    }
}
