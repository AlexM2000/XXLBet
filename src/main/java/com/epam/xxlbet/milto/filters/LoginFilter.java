package com.epam.xxlbet.milto.filters;

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
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_BOOKMAKER_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_CREATE_TEAM_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_PROFILE_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_CREATE_TEAM;

/**
 * LoginFilter.
 * If client executes specific commands, checks if client logged in.
 * If not, redirects client to login page.
 *
 * @author Aliaksei Milto
 */
@WebFilter(urlPatterns = "/xxlbet", filterName = "LoginFilter")
public class LoginFilter implements Filter {
    private static final String LOGIN_PAGE = "/login";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        switch (servletRequest.getParameter(COMMAND)) {
            case GET_PROFILE_PAGE:
            case GET_BOOKMAKER_PAGE:
            case GET_ADMIN_PAGE:
            case GET_BET_CREATE_PAGE:
            case GET_CREATE_TEAM_PAGE:
            case POST_CREATE_TEAM:
                if (request.getSession().getAttribute("login") == null || request.getSession().getAttribute("role") == null) {
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
