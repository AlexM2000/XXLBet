package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLanguageCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(GetLanguageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            String locale = request.getParameter("lang") != null ? request.getParameter("lang") : "en";
            response.addCookie(new Cookie("language", locale));
            response.getWriter().print("ok");
            response.getWriter().flush();
        } catch (final IOException e) {
            LOG.error("Something went wrong during changing language", e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
