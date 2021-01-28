package com.epam.xxlbet.milto.context.impl;

import com.epam.xxlbet.milto.context.RequestContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * HttpServletRequestContext.
 * W
 *
 * @author Aliaksei Milto
 */
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

    @Override
    public String getContentType() {
        return request.getContentType();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        request.setCharacterEncoding("UTF-8");
        return request.getReader();
    }
}
