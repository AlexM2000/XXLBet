package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;

/**
 * CommandFactory.
 *
 * @author Aliaksei Milto
 */
public interface CommandFactory {
    String COMMAND = "command";
    String GET_HOME_COMMAND = "home";
    String LANGUAGE_COMMAND = "lang";
    String GET_REGISTRATION_PAGE = "registration_page";
    String POST_REGISTRATION_COMMAND = "registration";
    String GET_CONFIRM_PAGE = "confirm_page";
    String POST_CONFIRM_COMMAND = "confirm";
    String GET_LOGIN_PAGE = "login_page";
    String POST_LOGIN = "login";
    String GET_PROFILE_PAGE = "profile";
    String GET_ALL_USER_BETS = "all_user_bets";
    String GET_WIN_USER_BETS = "win_user_bets";
    String GET_DEFEAT_USER_BETS = "defeat_user_bets";
    String POST_LOGOUT = "logout";
    String GET_ADMIN_PAGE = "admin_page";
    String GET_BOOKMAKER_PAGE = "bookmaker_page";

    Command createCommand(String commandName);


}
