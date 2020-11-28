package xxl.bet.milto.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Contains info about error.
 *
 * @author alexm2000
 */
class Error {
    private static final Logger LOG = LoggerFactory.getLogger(Error.class);
    private static final String MESSAGES_FILE_NAME = "/messages_";
    private static final String ERROR_MSG = "Could not load error message from properties file!";

    private String code;
    private Object[] args;
    private String locale;

    Error(final String code, final String locale, final Object[] args) {
        this.code = code;
        this.locale = locale;
        this.args = args;
    }

    public String getFormattedMsg() {
        Properties properties = new Properties();
        String msg = "";
        try {
            properties.load(Error.class.getResourceAsStream(MESSAGES_FILE_NAME + locale + ".properties"));
            msg = String.format(properties.getProperty(code), args);
        } catch (final IOException e) {
            LOG.error(ERROR_MSG);
            msg = "Internal error occured. Please contact administrator.";
        }

        return msg;
    }

    public String getCode() {
        return code;
    }
}
