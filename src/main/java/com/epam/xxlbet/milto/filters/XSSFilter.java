package com.epam.xxlbet.milto.filters;


import com.epam.xxlbet.milto.filters.xss.XSSHttpServletRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSSFilter.
 * Wraps original HttpServletRequest with wrapper
 * that removes all strings related to javascript from request parameters.
 *
 * @author Aliaksei Milto
 */
@WebFilter(filterName = "XSSFilter", urlPatterns = "/xxlbet")
public class XSSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {

    }
}
