package com.epam.xxlbet.milto.exceptions;

/**
 * UnknownCommandException.
 *
 * @author Aliaksei Milto
 */
public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(final String message) {
        super(message);
    }
}
