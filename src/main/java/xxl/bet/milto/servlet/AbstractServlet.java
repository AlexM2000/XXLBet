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
    private static final int DEFAULT_PAGE_SIZE = 20;
    private Map<String, String> errorIdByErrorMsg = new HashMap<>();
    private ObjectMapper mapper = new ObjectMapper();

    protected Map<String, String> validate(final Object object, final String locale, final Validator validator)
    {
        final Errors errors = new Errors();

        validator.validate(object, errors, locale);
        if (errors.hasErrors()) {
            errorIdByErrorMsg.putAll(errors.getErrors());
            errorIdByErrorMsg.put("status", "failed");

        } else {
            errorIdByErrorMsg.put("status", "verified");
        }

        return errorIdByErrorMsg;
    }

    protected <T> T getRequestBody(final HttpServletRequest request, final Class<T> clazz) {
        T entity = null;

        try {
            entity = mapper.readValue(request.getInputStream(), clazz);
        } catch (final IOException e) {
            LOG.error("Something went wrong while reading {} request body...", clazz);
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

    protected Map<String, String> getErrorIdByErrorMsg() {
        return errorIdByErrorMsg;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }
}
