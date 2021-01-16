package com.epam.xxlbet.milto.context;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * RequsetContext.
 *
 * @author alexm2000
 */
public interface RequestContext {

    /**
     * Set attribute of request.
     *
     * @param key key
     * @param value value
     */
    void setAttribute(String key, Object value);

    /**
     * Get parameter from request.
     *
     * @param param parameter
     * @return parameter value
     */
    String getParameter(String param);

    /**
     * Set attribute to session.
     *
     * @param key key
     * @param value value
     */
    void setSessionAttribute(String key, Object value);

    /**
     * Get cookies from the request.
     *
     * @return {@link Cookie[]}
     */
    Cookie[] getCookies();

    /**
     * Get parameter from session.
     *
     * @param key key
     * @return value
     */
    Object getSessionAttribute(String key);

    /**
     * Get reader of the request body.
     *
     * @return reader of the request body
     */
    BufferedReader getReader() throws IOException;

    /**
     * Invalidate session.
     */
    void invalidateSession();

    /**
     * Get content type of request body.
     *
     * @return content type
     */
    String getContentType();
}
