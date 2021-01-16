package com.epam.xxlbet.milto.servlet;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.factory.CommandFactory;
import com.epam.xxlbet.milto.command.factory.CommandFactoryImpl;
import com.epam.xxlbet.milto.context.HttpServletRequestContext;
import com.epam.xxlbet.milto.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.exceptions.UnknownCommandException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Process the request
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            RequestContext requestContext = new HttpServletRequestContext(request);
            ResponseContext responseContext = new HttpServletResponseContext(response);
            CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            String commandName = request.getParameter(COMMAND_PARAMETER);
            Command command = commandFactory.createCommand(commandName);
            CommandResult commandResult = command.execute(requestContext, responseContext);
            dispatch(request, response, commandResult);
        } catch (final ServiceException | UnknownCommandException | IOException e) {
            LOG.error("Something went wrong during processing request...", e);
        }
    }

    /**
     * Decide what to do after command execution depending on given CommandResult.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param commandResult CommandResult
     * @throws IOException if error while writing response occurred
     * @throws ServletException if error while redirecting or forwarding occurred
     */
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
                if (commandResult.getResponseBody() instanceof String) {
                    writeString(response, (String) commandResult.getResponseBody());
                } else {
                    writeJSON(response, commandResult.getResponseBody());
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown command result: " + commandResult.getCommandResultType());
        }
    }

    /**
     * Write string to response.
     *
     * @param response HttpServletResponse
     * @param responseBody String that will be written to response
     */
    private void writeString(HttpServletResponse response, String responseBody) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(responseBody);
    }

    /**
     * Write JSON response body to response.
     *
     * @param response HttpServletResponse
     * @param responseBody String that will be written to response
     */
    private void writeJSON(HttpServletResponse response, Object responseBody) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        OBJECT_MAPPER.writeValue(response.getWriter(), responseBody);
    }
}