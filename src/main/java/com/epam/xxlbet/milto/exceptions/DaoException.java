package com.epam.xxlbet.milto.exceptions;

/**
 * DaoException.
 * Thrown when there are errors related to sql in dao.
 *
 * @author Aliaksei Milto
 */
public class DaoException extends RuntimeException {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
