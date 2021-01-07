package com.epam.xxlbet.milto.filters.authenticator;

import javax.servlet.http.HttpSession;

/**
 * Authenticator.
 * Should check if client has rights to execute command.
 *
 * @author Aliaksei Milto
 */
public interface Authenticator {

    boolean hasAuthority(HttpSession httpSession, String commandName);

}
