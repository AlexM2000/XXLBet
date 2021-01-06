package com.milto.filter.authentificator;

import com.epam.xxlbet.milto.filters.authenticator.Authenticator;
import com.epam.xxlbet.milto.filters.authenticator.AuthenticatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTest {
    private Authenticator authenticator = AuthenticatorImpl.getInstance();

    private HttpSession httpSession;

    @Before
    public void setup() {
        httpSession = mock(HttpSession.class);
    }

    @Test
    public void testAuthority_ShouldAuthorizeAll() {
        assertTrue(authenticator.hasAuthority(httpSession, GET_HOME_COMMAND));
        assertTrue(authenticator.hasAuthority(httpSession, LANGUAGE_COMMAND));
        assertTrue(authenticator.hasAuthority(httpSession, GET_LOGIN_PAGE));
        assertTrue(authenticator.hasAuthority(httpSession, GET_REGISTRATION_PAGE));
        assertTrue(authenticator.hasAuthority(httpSession, GET_CONFIRM_PAGE));
        assertTrue(authenticator.hasAuthority(httpSession, POST_LOGIN));
        assertTrue(authenticator.hasAuthority(httpSession, POST_REGISTRATION_COMMAND));
        assertTrue(authenticator.hasAuthority(httpSession, POST_CONFIRM_COMMAND));
        assertTrue(authenticator.hasAuthority(httpSession, POST_LOGOUT));
    }

    @Test
    public void testAuthority_ShouldNotAuthorizeWithoutSessionAttributes() {
        assertFalse(authenticator.hasAuthority(httpSession, GET_PROFILE_PAGE));
        assertFalse(authenticator.hasAuthority(httpSession, GET_ALL_USER_BETS));
        assertFalse(authenticator.hasAuthority(httpSession, GET_WIN_USER_BETS));
        assertFalse(authenticator.hasAuthority(httpSession, GET_DEFEAT_USER_BETS));

        assertFalse(authenticator.hasAuthority(httpSession, GET_ADMIN_PAGE));
        assertFalse(authenticator.hasAuthority(httpSession, GET_BOOKMAKER_PAGE));
    }

    @Test
    public void testAuthority_ShouldAuthorizeClientRequests() {
        // when
        when(httpSession.getAttribute("login")).thenReturn("SomeRandomAuthorizedUserLogin");
        when(httpSession.getAttribute("role")).thenReturn("client");

        // then
        assertTrue(authenticator.hasAuthority(httpSession, GET_PROFILE_PAGE));
        assertTrue(authenticator.hasAuthority(httpSession, GET_ALL_USER_BETS));
        assertTrue(authenticator.hasAuthority(httpSession, GET_WIN_USER_BETS));
        assertTrue(authenticator.hasAuthority(httpSession, GET_DEFEAT_USER_BETS));
    }

    @Test
    public void testAuthority_ShouldAuthorizeAdminRequests() {
        // when
        when(httpSession.getAttribute("role")).thenReturn("admin");

        // then
        assertTrue(authenticator.hasAuthority(httpSession, GET_ADMIN_PAGE));
    }

    @Test
    public void testAuthority_ShouldAuthorizeBookmakerRequests() {
        // when
        when(httpSession.getAttribute("role")).thenReturn("bookmaker");

        // then
        assertTrue(authenticator.hasAuthority(httpSession, GET_BOOKMAKER_PAGE));
    }
}
