package xxl.bet.milto.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import xxl.bet.milto.utils.Errors;
import xxl.bet.milto.validator.Validator;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class AbstractServlet extends HttpServlet {
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
