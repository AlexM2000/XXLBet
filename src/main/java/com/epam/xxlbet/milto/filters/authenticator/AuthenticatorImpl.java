package com.epam.xxlbet.milto.filters.authenticator;

import javax.servlet.http.HttpSession;

import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_ADMIN_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_ALL_USER_BETS;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_BOOKMAKER_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_CONFIRM_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_DEFEAT_USER_BETS;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_HOME_COMMAND;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_LOGIN_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_PROFILE_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_REGISTRATION_PAGE;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.GET_WIN_USER_BETS;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.LANGUAGE_COMMAND;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_CONFIRM_COMMAND;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_LOGIN;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_LOGOUT;
import static com.epam.xxlbet.milto.command.factory.CommandFactory.POST_REGISTRATION_COMMAND;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.ADMIN_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BOOKMAKER_ROLE;
import static java.util.Arrays.asList;

/**
 * AuthenticatorImpl.
 *
 * @author Aliaksei Milto
 */
public final class AuthenticatorImpl implements Authenticator {
    private static AuthenticatorImpl instance;

    private AuthenticatorImpl() {
    }

    public static AuthenticatorImpl getInstance() {
        if (instance == null) {
            instance = new AuthenticatorImpl();
        }

        return instance;
    }

    @Override
    public boolean hasAuthority(HttpSession httpSession, String commandName) {
        switch (commandName) {
            default:
                throw new IllegalArgumentException("Unknown command " + commandName);
            case GET_HOME_COMMAND:
            case LANGUAGE_COMMAND:
            case GET_LOGIN_PAGE:
            case GET_REGISTRATION_PAGE:
            case GET_CONFIRM_PAGE:
            case POST_LOGIN:
            case POST_REGISTRATION_COMMAND:
            case POST_CONFIRM_COMMAND:
            case POST_LOGOUT:
                return true;

            case GET_PROFILE_PAGE:
            case GET_ALL_USER_BETS:
            case GET_WIN_USER_BETS:
            case GET_DEFEAT_USER_BETS:
                return httpSession.getAttribute("login") != null && httpSession.getAttribute("role") != null;

            case GET_ADMIN_PAGE:
                return asList(ADMIN_ROLE, BOOKMAKER_ROLE).contains(httpSession.getAttribute("role"));

            case GET_BOOKMAKER_PAGE:
                return BOOKMAKER_ROLE.equals(httpSession.getAttribute("role"));
        }
    }
}
