package com.epam.xxlbet.milto.servlet;

import com.epam.xxlbet.milto.command.context.RequestContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

class HttpServletRequestContext implements RequestContext {
    private final HttpServletRequest request;

    HttpServletRequestContext(final HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    @Override
    public String getParameter(String param) {
        return request.getParameter(param);
    }

    @Override
    public void setSessionAttribute(String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    @Override
    public Cookie[] getCookies() {
        return request.getCookies();
    }
}
