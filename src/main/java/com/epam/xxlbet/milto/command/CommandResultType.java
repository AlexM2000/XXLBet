package com.epam.xxlbet.milto.command;

/**
 * CommandResultType.
 *
 * REDIRECT means that client will be redirected.
 *
 * FORWARD means that client will be forwarded.
 *
 * WRITE_DIRECT_TO_RESPONSE means, that anwser (json mostly) is written to response (not request.setAttribute...)
 * and is processed by ajax in frontend.
 *
 * @author Aliaksei Milto
 */
public enum CommandResultType {
    REDIRECT, FORWARD, WRITE_DIRECT_TO_RESPONSE;
}
