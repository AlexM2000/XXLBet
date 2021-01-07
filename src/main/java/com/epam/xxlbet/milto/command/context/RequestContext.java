package com.epam.xxlbet.milto.command.context;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * RequsetContext.
 *
 * @author alexm2000
 */
public interface RequestContext {
    void setAttribute(String key, Object value);
    String getParameter(String param);
    void setSessionAttribute(String key, Object value);
    Cookie[] getCookies();
    Object getSessionAttribute(String key);
    public BufferedReader getReader() throws IOException;
    void invalidateSession();
}
