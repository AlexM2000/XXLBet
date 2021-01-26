package com.epam.xxlbet.milto.context.impl;

import com.epam.xxlbet.milto.context.ResponseContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServletResponseContext.
 *
 * @author Aliaksei Milto
 */

public class HttpServletResponseContext implements ResponseContext {
    private final HttpServletResponse response;

    public HttpServletResponseContext(final HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }
}
