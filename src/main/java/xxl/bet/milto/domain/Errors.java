package xxl.bet.milto.domain;

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
}
