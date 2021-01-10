package com.epam.xxlbet.milto.utils;

/**
 * All constants kept in one place for convenience.
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

    public static final String FILE_WITH_QUERIES_FOR_TABLE_MATCHES = "queries_matches.properties";
    public static final String SELECT_INCOMPLETE_MATCHES_PROPERTY_ID = "select.all.incomplete.matches";

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

    public static final String FILE_WITH_QUERIES_FOR_TABLE_VERIFICATION_TOKENS = "queries_verification_tokens.properties";
    public static final String SELECT_TOKEN_BY_TOKEN_PROPERTY_ID = "select.token.by.token";
    public static final String INSERT_TOKEN_PROPERTY_ID = "insert.into.verification-token";
    public static final String DELETE_USER_TOKEN_PROPERTY_ID = "delete.user.token";

    public static final String FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS = "queries_opponents.properties";
    public static final String SELECT_OPPONENTS_BY_ID = "select.opponent.by.id";
    public static final String SELECT_OPPONENTS_FROM_MATCH = "select.opponents.from.match";
}
