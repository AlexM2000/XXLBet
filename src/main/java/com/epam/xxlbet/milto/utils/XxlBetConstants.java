package com.epam.xxlbet.milto.utils;

/**
 * XxlBetConstants.
 * Almost all constants kept in one place for convenience.
 *
 * @author Aliaksei Milto
 */
public final class XxlBetConstants {
    private XxlBetConstants() { }

    public static final String PROJECT_PROPERTIES = "project.properties";
    public static final String MAIL_PROPERTIES = "mail.properties";

    public static final String MESSAGES_EN_PROPERTIES = "messages_en.properties";
    public static final String MESSAGES_RU_PROPERTIES = "messages_ru.properties";
    public static final String MESSAGES_BE_PROPERTIES = "messages_be.properties";

    public static final String SECRET_KEY = "xxl.bet.milto.security.secret-key";

    public static final String ADMIN_ROLE = "admin";
    public static final String CLIENT_ROLE = "client";
    public static final String BOOKMAKER_ROLE = "bookmaker";

    public static final String ACTIVE_STATUS = "active";
    public static final String BANNED_STATUS = "banned";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_USER_INFO = "queries_user_info.properties";
    public static final String SELECT_ALL_USER_INFO = "select.all.user-info";
    public static final String INSERT_INTO_USER_INFO = "insert.into.user-info";
    public static final String SELECT_USER_INFO_BY_EMAIL = "select.from.user.info.by.email";
    public static final String UPDATE_USER_INFO = "update.user-info";
    public static final String UPDATE_USER_BALANCE_AFTER_BET = "update.user.balance.after.bet";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_MATCHES = "queries_matches.properties";
    public static final String INSERT_INTO_MATCHES = "insert.into.matches";
    public static final String SELECT_FUTURE_MATCHES = "select.all.future.matches";
    public static final String SELECT_ALL_ONLINE_AND_INCOMPLETE_MATCHES = "select.all.online.and.incomplete.matches";
    public static final String DELETE_ALL_FINISHED_MATCHES = "delete.all.finished.matches";
    public static final String SELECT_MATCHES_BY_TOURNAMENT = "select.matches.by.tournament";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_MATCH_RESULTS = "queries_match_results.properties";
    public static final String CREATE_MATCH_RESULT = "create.match-result";
    public static final String SELECT_MATCH_RESULT_BY_MATCH = "select.match-result.by.match";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_BETS = "queries_bets.properties";
    public static final String SELECT_INCOMPLETE_BETS_BY_USER_ID = "select.incomplete.bets.by.user";
    public static final String SELECT_BETS_BY_USER_ID = "select.bets.by.user";
    public static final String SELECT_WINNING_BETS_ID = "select.winning.bets.by.user";
    public static final String SELECT_DEFEAT_BETS_ID = "select.defeat.bets.by.user";
    public static final String INSERT_INTO_BETS = "insert.into.bets";
    public static final String SELECT_BET_BY_USER_ID = "select.bet.by.user_id";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_USERS = "queries_users.properties";
    public static final String INSERT_INTO_USER_PROPERTY_ID = "insert.into.user";
    public static final String DELETE_FROM_USER_PROPERTY_ID = "delete.from.users";
    public static final String DELETE_ALL_UNCONFIRMED_USERS = "delete.all.unconfirmed.users";
    public static final String UPDATE_USER_PROPERTY_ID = "update.users";
    public static final String SELECT_BY_ID_PROPERTY_ID = "select.user.by.id";
    public static final String SELECT_BY_EMAIL_PROPERTY_ID = "select.user.by.email";
    public static final String SELECT_BY_EMAIL_AND_PASSWORD_ID = "select.user.by.email.and.password";
    public static final String SELECT_BY_PHONENUMBER_ID = "select.user.by.phonenumber";
    public static final String SELECT_BY_PHONENUMBER_AND_PASSWORD_ID = "select.user.by.phonenumber.and.password";
    public static final String SELECT_UNCONFIRMED_USERS = "select.unconfirmed.users";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_ROLES = "queries_roles.properties";
    public static final String SELECT_USER_ROLE_BY_EMAIL = "select.role.from.user-info.by.email";
    public static final String SELECT_USER_ROLE_BY_ID = "select.role.by.id";
    public static final String SELECT_USER_ROLE_BY_NAME = "select.role.by.name";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_STATUSES = "queries_statuses.properties";
    public static final String SELECT_USER_STATUS_BY_EMAIL = "select.status.from.user-info.by.email";
    public static final String SELECT_USER_STATUS_BY_ID = "select.status.by.id";
    public static final String SELECT_USER_STATUS_BY_NAME = "select.status.by.name";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_SPORTS = "queries_sports.properties";
    public static final String SELECT_ALL_SPORTS = "select.all.sports";
    public static final String SELECT_SPORT_BY_NAME = "select.sport.by.name";
    public static final String INSERT_INTO_SPORTS = "insert.into.sports";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_TOURNAMENTS = "queries_tournaments.properties";
    public static final String SELECT_TOURNAMENTS_BY_SPORT_NAME = "select.tournaments.by.sport.name";
    public static final String SELECT_TOURNAMENTS_BY_NAME = "select.tournaments.by.name";
    public static final String DELETE_ALL_FINISHED_TOURNAMENTS = "delete.all.finished.tournaments";
    public static final String INSERT_INTO_TOURNAMENT = "insert.into.tournament";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_VERIFICATION_TOKENS = "queries_verification_tokens.properties";
    public static final String SELECT_TOKEN_BY_TOKEN_PROPERTY_ID = "select.token.by.token";
    public static final String INSERT_TOKEN_PROPERTY_ID = "insert.into.verification-token";
    public static final String DELETE_USER_TOKEN_PROPERTY_ID = "delete.user.token";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS = "queries_opponents.properties";
    public static final String SELECT_OPPONENTS_BY_ID = "select.opponent.by.id";
    public static final String SELECT_OPPONENT_BY_NAME = "select.opponent.by.name";
    public static final String SELECT_OPPONENTS_FROM_MATCH = "select.opponents.from.match";
    public static final String SELECT_OPPONENTS_BY_TOURNAMENT_NAME = "select.opponents.by.tournament.name";
    public static final String ADJUST_OPPONENT_TO_MATCH = "adjust.opponent.to.match";
    public static final String INSERT_INTO_OPPONENTS = "insert.into.opponents";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_CREDIT_CARDS = "queries_credit_cards.properties";
    public static final String LINK_CREDIT_CARD = "link.credit.card";
    public static final String SELECT_CREDIT_CARD_BY_NUMBER = "select.credit.card.by.number";
    public static final String SELECT_CREDIT_CARD_BY_USER = "select.credit.cards.by.user";
    public static final String UNLINK_CREDIT_CARD = "unlink.credit.card";

    public static final String
            FILE_WITH_QUERIES_FOR_TABLE_PASSWORD_CHANGE_REQUESTS = "queries_password_change_requests.properties";
    public static final String INSERT_INTO_PASSWORD_CHANGE_REQUESTS = "insert.into.password.change.requests";
    public static final String SELECT_PASSWORD_CHANGE_REQUEST_BY_TOKEN = "select.password.change.request.by.token";
    public static final String DELETE_PASSWORD_CHANGE_REQUEST_BY_TOKEN = "delete.password.change.request.by.token";
    public static final String DELETE_PASSWORD_CHANGE_REQUEST_BY_USER = "delete.password.change.request.by.user";
    public static final String DELETE_ALL_EXPIRED_PASSWORD_CHANGE_REQUESTS = "delete.all.expired.password.change.requests";
}
