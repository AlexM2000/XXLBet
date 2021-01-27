package com.epam.xxlbet.milto.command;

/**
 * CommandResultType.
 *
 * {@link #REDIRECT} means that client will be redirected.
 *
 * {@link #FORWARD} means that client will be forwarded.
 *
 * {@link #WRITE_DIRECT_TO_RESPONSE} means, that answer (json mostly) is written to response (not request.setAttribute...)
 * and processed by ajax in frontend.
 *
 * @author Aliaksei Milto
 */
public enum CommandResultType {
    REDIRECT, FORWARD, WRITE_DIRECT_TO_RESPONSE;
}
