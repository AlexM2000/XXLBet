package com.epam.xxlbet.milto.exceptions;

/**
 * ServiceException.
 *
 * @author Aliaksei Milto
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
