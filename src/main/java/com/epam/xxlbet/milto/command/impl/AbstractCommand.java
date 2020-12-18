package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.requestbody.LoginRequest;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCommand.class);
    protected static final String STATUS = "status";
    protected static final String FAILED = "failed";
    protected static final String VERIFIED = "verified";
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, String> errors = new HashMap<>();

    protected <T> T getRequestBody(final HttpServletRequest request, final Class<T> clazz) {
        T entity = null;

        if (RegistrationRequest.class.equals(clazz)) {
            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setEmail(request.getParameter("body[email]"));
            registrationRequest.setPhoneNumber(request.getParameter("body[phoneNumber]"));
            registrationRequest.setSurname(request.getParameter("body[surname]"));
            registrationRequest.setName(request.getParameter("body[name]"));
            registrationRequest.setSecondName(request.getParameter("body[secondName]"));
            registrationRequest.setPassword(request.getParameter("body[password]"));
            registrationRequest.setRepeatPassword(request.getParameter("body[repeatPassword]"));
            try {
                registrationRequest.setBirthDate(new SimpleDateFormat("YYYY-mm-dd").parse(request.getParameter("body[birthDate]")));
            } catch (ParseException e) {
                LOG.error("Error parsing date at getRequestBody...", e);
            }
            return (T) registrationRequest;
        }

        if (LoginRequest.class.equals(clazz)) {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setLogin(request.getParameter("body[login]"));
            loginRequest.setPassword(request.getParameter("body[password]"));
            return (T) loginRequest;
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

    protected String getCurrentLocale(final HttpServletRequest request) {
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
