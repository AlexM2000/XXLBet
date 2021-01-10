package com.epam.xxlbet.milto.servlet;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.HttpServletRequestContext;
import com.epam.xxlbet.milto.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
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
            RequestContext requestContext = new HttpServletRequestContext(request);
            ResponseContext responseContext = new HttpServletResponseContext(response);
            CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            String commandName = request.getParameter(COMMAND_PARAMETER);
            Command command = commandFactory.createCommand(commandName);
            CommandResult commandResult = command.execute(requestContext, responseContext);
            dispatch(request, response, commandResult);
        } catch (final ServiceException | IOException e) {
            LOG.error("Something went wrong during processing request...", e);
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult commandResult)
            throws ServletException, IOException {
        String page = commandResult.getPage();
        switch (commandResult.getCommandResultType()) {
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
                throw new IllegalArgumentException("Unknown command: " + commandResult.getCommandResultType());
        }
    }
}