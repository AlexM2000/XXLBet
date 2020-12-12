package xxl.bet.milto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.exceptions.PropertyNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Contains info about encountered errors.
 *
 * @author alexm2000
 */
public class Errors {
    private List<Error> errors;

    public Errors() {
        errors = new ArrayList<>();
    }

    public void reject(final String msgId, final String locale, final Object... args) {
        errors.add(new Error(msgId, locale, args));
    }

    public Map<String, String> getErrors() {
        return errors.stream()
                .collect(toMap(Error::getCode, Error::getFormattedMsg));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * Contains info about error.
     *
     * @author alexm2000
     */
    private static class Error {
        private static final Logger LOG = LoggerFactory.getLogger(Error.class);
        private static final String MESSAGES_FILE_NAME = "messages_";
        private static final String ERROR_MSG = "Could not load error message from properties file!";

        private String code;
        private Object[] args;
        private String locale;

        Error(final String code, final String locale, final Object[] args) {
            this.code = code;
            this.locale = locale;
            this.args = args;
        }

        String getFormattedMsg() {
            LOG.debug("File {}", MESSAGES_FILE_NAME + locale + ".properties");
            return String.format(PropertyLoader.getInstance().getStringProperty(MESSAGES_FILE_NAME + locale + ".properties", code)
                    .orElseThrow(() -> new PropertyNotFoundException(ERROR_MSG + " " + code + " " + locale)), args);
        }

        String getCode() {
            return code;
        }
    }
}
