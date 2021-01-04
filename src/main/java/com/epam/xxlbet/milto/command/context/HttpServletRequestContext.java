package com.epam.xxlbet.milto.command.context;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestContext implements RequestContext {
    private final HttpServletRequest request;

    public HttpServletRequestContext(final HttpServletRequest request) {
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

    @Override
    public Object getSessionAttribute(String key) {
        return request.getSession().getAttribute(key);
    }

    @Override
    public void invalidateSession() {
        request.getSession().invalidate();
    }
}
