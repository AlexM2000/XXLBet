package com.epam.xxlbet.milto.filters;

import com.epam.xxlbet.milto.domain.Status;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.xxlbet.milto.command.factory.CommandFactory.COMMAND;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_ADMIN_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_BET_CREATE_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_PROFILE_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_CREATE_BET;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_CREATE_MATCH;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BANNED_STATUS;

/**
 * StatusFilter.
 * If client executes specific commands, checks if client has rights to perform it.
 * Checks client status (is status ACTIVE or BANNED).
 * If banned, redirects to ban page and command not executed.
 *
 * @author Aliaksei Milto
 */
@WebFilter(filterName = "StatusFilter", urlPatterns = "/xxlbet")
public class StatusFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        switch (request.getParameter(COMMAND)) {
            case GET_PROFILE_PAGE:
            case GET_BET_CREATE_PAGE:
            case GET_ADMIN_PAGE:
            case POST_CREATE_BET:
            case POST_CREATE_MATCH:
                if (BANNED_STATUS.equals(((Status) request.getSession().getAttribute("status")).getName())) {
                    response.sendRedirect("/ban");
                    return;
                }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
