package com.epam.xxlbet.milto.filters.authenticator;

import javax.servlet.http.HttpSession;

public interface Authenticator {

    boolean hasAuthority(HttpSession httpSession, String commandName);

}
