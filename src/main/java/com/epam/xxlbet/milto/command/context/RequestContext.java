package com.epam.xxlbet.milto.command.context;

import javax.servlet.http.Cookie;

public interface RequestContext {
    void setAttribute(String key, Object value);
    String getParameter(String param);
    void setSessionAttribute(String key, Object value);
    Cookie[] getCookies();
}
