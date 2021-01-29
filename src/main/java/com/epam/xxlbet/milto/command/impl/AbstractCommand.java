package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
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
 * Parent of all {@link Command} implementations.
 * Contains useful methods, such as validating objects,
 * reading request body and other.
 *
 * @author Aliaksei Milto
 */
public abstract class AbstractCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCommand.class);
    protected static final String ERROR_READING_REQUEST_BODY = "Error reading request body of ";
    protected static final String STATUS = "status";
    protected static final String FAILED = "failed";
    protected static final String VERIFIED = "verified";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, String> errors = new HashMap<>();

    /**
     * Get request body from the request
     * and try to convert it to given {@link Class<T>} instance.
     * If conversion was successful, return {@link T} instance with data from request body.
     * Otherwise throw {@link ServiceException} and return null.
     *
     * @param request request context.
     * @param clazz Class, to which convert request body
     * @return {@link T} instance of request body.
     * @throws ServiceException if error occurred when reading request body
     */
    protected <T> T getRequestBody(final RequestContext request, final Class<T> clazz) {
        T entity = null;

        try {
            if (request.getContentType().equals("text/plain; charset=UTF-8") && clazz.equals(String.class)) {
                entity = (T) request.getReader().lines().collect(Collectors.joining());
            } else if (request.getContentType().equals("application/json; charset=UTF-8")) {
                String body = request.getReader().lines().collect(Collectors.joining());
                entity = getMapper().readValue(body, clazz);
            }
        } catch (IOException e) {
            throw new ServiceException(ERROR_READING_REQUEST_BODY + clazz, e);
        }

        return entity;
    }

    /**
     * Validate given object with given validator.
     * If there were validation errors, appends to {@link #errors} map pair of key and value
     * where key is error message id and value is localized error message.
     * Additionally appends to map flag pair, where key is {@link #STATUS}
     * and value depends on if validation errors are present.
     *
     * {@link #FAILED} if present and {@link #VERIFIED} if there are no errors
     *
     * @param object Object to validate.
     * @param locale Locale of error message.
     * @param validator {@link Validator} that performs validation of object.
     */
    protected void validate(final Object object, final String locale, final Validator validator) {
        final Errors errors = new Errors();
        errors.setLocale(locale);

        validator.validate(object, errors);

        if (errors.hasErrors()) {
            this.errors.putAll(errors.getErrors());
            this.errors.put(STATUS, FAILED);
        } else if (!this.errors.containsKey(STATUS) || (this.errors.containsKey(STATUS) && !this.errors.get(STATUS).equals(FAILED))) {
            this.errors.put(STATUS, VERIFIED);
        }
    }

    /**
     * Get current locale from cookies in the request.
     * If no locale present in cookies, return "en" (english) as default locale.
     *
     * @param request request context.
     * @return current locale as string.
     */
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
