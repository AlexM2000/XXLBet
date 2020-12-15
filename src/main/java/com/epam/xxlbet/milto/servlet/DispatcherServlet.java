package com.epam.xxlbet.milto.servlet;

/*
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherServlet.class);
    protected static final String STATUS = "status";
    protected static final String FAILED = "failed";
    protected static final String VERIFIED = "verified";
    private static final int DEFAULT_PAGE_SIZE = 20;
    private Map<String, String> errors = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();

    protected void validate(final Object object, final String locale, final Validator validator)
    {
        final Errors errors = new Errors();

        validator.validate(object, errors, locale);

        if (errors.hasErrors()) {
            this.errors.putAll(errors.getErrors());
            this.errors.put(STATUS, FAILED);
        } else if (!this.errors.containsKey(STATUS) || (this.errors.containsKey(STATUS) && !this.errors.get(STATUS).equals(FAILED))) {
            this.errors.put(STATUS, VERIFIED);
        }
    }

    protected <T> T getRequestBody(final HttpServletRequest request, final Class<T> clazz) {
        T entity = null;

        try {
            entity = mapper.readValue(request.getInputStream(), clazz);
        } catch (final IOException e) {
            LOG.error("Something went wrong while reading {} request body...", clazz, e);
        }

        return entity;
    }

    protected String getCurrentLocale(final HttpServletRequest request) {
        String locale = "en";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie temp : cookies) {
                if (temp.getName().equals("language")) {
                    locale = temp.getValue();
                }
            }
        }

        return locale;
    }

    protected static int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    protected Map<String, String> getErrors() {
        return errors;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }

    protected static Logger getLogger() {
        return LOG;
    }
}
*/

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.factory.CommandFactory;
import com.epam.xxlbet.milto.command.factory.CommandFactoryImpl;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/xxlbet")
public class DispatcherServlet extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";
    private static final Logger LOG = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            String commandName = request.getParameter(COMMAND_PARAMETER);
            Command command = commandFactory.createCommand(commandName);
            CommandResult commandResult = command.execute(request, response);
            dispatch(request, response, commandResult);
        } catch (final ServiceException | IOException e) {
            LOG.error("Something wwent wrong during processing request...", e);
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult commandResult)
            throws ServletException, IOException {
        String page = commandResult.getPage();
        switch (commandResult.getCommandType()) {
            case REDIRECT:
                String contextPath = getServletContext().getContextPath();
                response.sendRedirect(contextPath + page);
                break;
            case FORWARD:
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
                requestDispatcher.forward(request, response);
                break;
            case WRITE_DIRECT_TO_RESPONSE:
                // do nothing, as in command answer already was written to response
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + commandResult.getCommandType());
        }
    }
}