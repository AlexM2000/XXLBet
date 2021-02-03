package com.epam.xxlbet.milto.exceptions;

/**
 * DaoException.
 * Thrown when there are errors in dao layer.
 *
 * @author Aliaksei Milto
 */
public class DaoException extends RuntimeException {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
