package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AbstractCommand.
 *
 * @author Aliaksei Milto
 */
public abstract class AbstractCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCommand.class);
    protected static final String STATUS = "status";
    protected static final String FAILED = "failed";
    protected static final String VERIFIED = "verified";
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> errors = new HashMap<>();

    protected <T> T getRequestBody(final RequestContext request, final Class<T> clazz) {
        T entity = null;

        try {
            String body = request.getReader().lines().collect(Collectors.joining());
            entity = getMapper().readValue(body, clazz);
        } catch (IOException e) {
            LOG.error("Something went wrong during reading " + clazz, e);
        }

        return entity;
    }

    protected void validate(final Object object, final String locale, final Validator validator)
    {
        final Errors errors = new Errors();

        validator.validate(object, errors, locale);

        if (errors.hasErrors()) {
            this.errors.putAll(errors.getErrors());
            this.errors.put(STATUS, FAILED);
        } else if (!this.errors.containsKey(STATUS) || (this.errors.containsKey(STATUS) && !this.errors.get(STATUS).equals(FAILED))) {
            this.errors.put(STATUS, VERIFIED);
        }
    }

    protected String getCurrentLocale(final RequestContext request) {
        String locale = "en";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie temp : cookies) {
                if (temp.getName().equals("language")) {
                    locale = temp.getValue();
                }
            }
        }

        return locale;
    }

    protected Map<String, String> getErrors() {
        return errors;
    }

    protected Logger getLogger() {
        return LOG;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }
}
