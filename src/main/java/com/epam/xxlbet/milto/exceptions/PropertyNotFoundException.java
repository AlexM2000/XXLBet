package com.epam.xxlbet.milto.exceptions;

/**
 * PropertyNotFoundException.
 * Thrown when {@link com.epam.xxlbet.milto.utils.PropertyLoader}
 * can't find requested property.
 *
 * @author Aliaksei Milto
 */
public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(final String message) {
        super(message);
    }
}
