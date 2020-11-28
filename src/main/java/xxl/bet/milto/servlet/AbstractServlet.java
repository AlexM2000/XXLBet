package xxl.bet.milto.servlet;

import xxl.bet.milto.domain.Errors;
import xxl.bet.milto.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class AbstractServlet {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private Map<String, String> errorIdByErrorMsg = new HashMap<>();

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
}
