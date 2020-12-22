package com.epam.xxlbet.milto.command.context;

import javax.servlet.http.Cookie;
import java.io.IOException;

public interface ResponseContext {
    void writeValue(Object value) throws IOException;
    void writeJSONValue(Object value) throws IOException;
    void addCookie(Cookie language);
}
