package com.epam.xxlbet.milto.command.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletResponseContext implements ResponseContext {
    private final HttpServletResponse response;
    private final ObjectMapper mapper;

    public HttpServletResponseContext(final HttpServletResponse response) {
        this.response = response;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void writeValue(Object value) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().print(value);
    }

    @Override
    public void writeJSONValue(Object value) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        mapper.writeValue(response.getWriter(), value);
    }

    @Override
    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }
}
