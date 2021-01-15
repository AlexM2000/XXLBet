package com.epam.xxlbet.milto.filters.authenticator;

import javax.servlet.http.HttpSession;

/**
 * Authenticator.
 * Check if client has rights to execute command.
 *
 * @author Aliaksei Milto
 */
public interface Authenticator {

    /**
     * Check if client has rights to execute given command.
     * Checks client role.
     *
     * @param httpSession session of client
     * @param commandName command name
     * @return true if has rights, false otherwise
     */
    boolean hasAuthority(HttpSession httpSession, String commandName);

}
