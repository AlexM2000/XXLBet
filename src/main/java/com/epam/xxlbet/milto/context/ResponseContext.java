package com.epam.xxlbet.milto.context;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * ResponseContext.
 *
 * @author Aliaksei Milto
 */
public interface ResponseContext {
    void writeValue(Object value) throws IOException;
    void writeJSONValue(Object value) throws IOException;
    void addCookie(Cookie language);
}
