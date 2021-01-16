package com.epam.xxlbet.milto.exceptions;

/**
 * DaoException.
 * Thrown when there are errors connected to sql in dao.
 *
 * @author Aliaksei Milto
 */
public class DaoException extends RuntimeException {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
