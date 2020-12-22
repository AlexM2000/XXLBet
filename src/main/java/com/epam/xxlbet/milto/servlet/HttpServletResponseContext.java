package com.epam.xxlbet.milto.servlet;

import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class HttpServletResponseContext implements ResponseContext {
    private final HttpServletResponse response;
    private final ObjectMapper mapper;

    HttpServletResponseContext(final HttpServletResponse response) {
        this.response = response;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void writeValue(Object value) throws IOException {
        response.getWriter().print(value);
    }

    @Override
    public void writeJSONValue(Object value) throws IOException {
        mapper.writeValue(response.getWriter(), value);
    }

    @Override
    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }
}
