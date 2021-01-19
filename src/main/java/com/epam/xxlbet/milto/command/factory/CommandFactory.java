package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;

/**
 * CommandFactory.
 *
 * @author Aliaksei Milto
 */
public interface CommandFactory {
    // Commands
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
    String GET_TOURNAMENTS_BY_SPORT = "get_tournaments_by_sport";
    String GET_OPPONENTS_BY_TOURNAMENT = "get_opponents_by_tournament";
    String POST_LOGOUT = "logout";
    String GET_ADMIN_PAGE = "admin_page";
    String GET_BOOKMAKER_PAGE = "bookmaker_page";
    String POST_CHANGE_USER_ROLE_AND_STATUS = "change_user_role_and_status";
    String POST_CREATE_MATCH = "create_match";
    String GET_BET_CREATE_PAGE = "bet_create_page";
    String GET_MATCHES_BY_TOURNAMENT = "get_matches_by_tournament";
    String POST_CREATE_BET = "create_bet";
    String GET_CREATE_SPORT_PAGE = "create_sport_page";
    String POST_CREATE_SPORT = "create_sport";
    String GET_CREATE_TOURNAMENT_PAGE = "create_tournament_page";
    String POST_CREATE_TOURNAMENT = "create_tournament";
    String GET_CREATE_TEAM_PAGE = "create_team_page";
    String POST_CREATE_TEAM = "create_team";
    String GET_CREATE_CREDIT_CARD_PAGE = "create_credit_card_page";
    String POST_CREATE_CREDIT_CARD = "create_credit_card";

    /**
     * Creates command depending on given command name.
     *
     * @param commandName command name
     * @return {@link Command}
     */
    Command createCommand(String commandName);


}
