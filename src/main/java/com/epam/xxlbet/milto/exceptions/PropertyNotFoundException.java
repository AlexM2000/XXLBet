package com.epam.xxlbet.milto.exceptions;

/**
 * PropertyNotFoundException.
 *
 * @author Aliaksei Milto
 */
public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(final String message) {
        super(message);
    }
}
