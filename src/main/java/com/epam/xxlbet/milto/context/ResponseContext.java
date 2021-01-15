package com.epam.xxlbet.milto.context;

import javax.servlet.http.Cookie;

/**
 * ResponseContext.
 *
 * @author Aliaksei Milto
 */
public interface ResponseContext {

    /**
     * Add cookie to the response.
     *
     * @param cookie {@link Cookie}
     */
    void addCookie(Cookie cookie);
}
