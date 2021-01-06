package com.epam.xxlbet.milto.filters;

import com.epam.xxlbet.milto.filters.authenticator.Authenticator;
import com.epam.xxlbet.milto.filters.authenticator.AuthenticatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@WebFilter(urlPatterns = "/xxlbet", filterName = "AuthorityFilter")
public class AuthorityFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorityFilter.class);
    private Authenticator authenticator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.authenticator = AuthenticatorImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (authenticator.hasAuthority(request.getSession(), request.getParameter(COMMAND))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOG.debug("User has no permission to execute command " + request.getParameter(COMMAND));
            response.sendRedirect("/views/no-authority.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
