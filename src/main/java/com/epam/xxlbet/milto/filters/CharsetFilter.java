package com.epam.xxlbet.milto.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * CharsetFilter.
 * Prevents question marks in response when RU or BLR language chosen.
 * More at https://stackoverflow.com/questions/138948/how-to-get-utf-8-working-in-java-webapps
 *
 * @author Aliaksei Milto
 */
@WebFilter(filterName = "CharsetFilter", urlPatterns = "/xxlbet")
public class CharsetFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding("UTF-8");
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    public void destroy() {
    }
}
