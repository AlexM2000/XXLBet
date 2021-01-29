package com.epam.xxlbet.milto.exceptions;

/**
 * ConnectionPoolException.
 *
 * @author Aliaksei Milto
 */
public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String s, Exception e) {
        super(s, e);
    }
}
