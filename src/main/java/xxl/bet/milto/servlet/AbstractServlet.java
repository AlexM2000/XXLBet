package xxl.bet.milto.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.utils.Errors;
import xxl.bet.milto.validator.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbstractServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractServlet.class);
    private static final String STATUS = "status";
    private static final String FAILED = "failed";
    private static final String VERIFIED = "verified";
    private static final int DEFAULT_PAGE_SIZE = 20;
    private Map<String, String> errors = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();

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

    protected <T> T getRequestBody(final HttpServletRequest request, final Class<T> clazz) {
        T entity = null;

        try {
            entity = mapper.readValue(request.getInputStream(), clazz);
        } catch (final IOException e) {
            LOG.error("Something went wrong while reading {} request body...", clazz, e);
        }

        return entity;
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

    protected static int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    protected Map<String, String> getErrors() {
        return errors;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }
}
