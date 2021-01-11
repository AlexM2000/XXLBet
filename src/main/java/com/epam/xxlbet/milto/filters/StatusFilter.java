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
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_BET_CREATE_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_PROFILE_PAGE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BANNED_STATUS;

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