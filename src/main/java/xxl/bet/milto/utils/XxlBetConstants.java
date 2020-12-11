package xxl.bet.milto.utils;

/**
 * All constants kept in one place for convenience.
 *
 * @author alexm2000
 */
public final class XxlBetConstants {
    private XxlBetConstants() { }

    public static final String PROJECT_PROPERTIES = "project.properties";
    public static final String SECRET_KEY = "xxl.bet.milto.security.secret-key";
    public static final String FILE_WITH_QUERIES_FOR_TABLE_MATCHES = "queries_matches.properties";
    public static final String SELECT_INCOMPLETE_MATCHES_PROPERTY_ID = "select.all.incomplete.matches";
    public static final String FILE_WITH_QUERIES_FOR_TABLE_BETS = "queries_bets.properties";
    public static final String SELECT_INCOMPLETE_BETS_BY_USER_ID = "select.incomplete.bets.by.user";
    public static final String FILE_WITH_QUERIES_FOR_TABLE_USERS = "queries_users.properties";
    public static final String INSERT_INTO_USER_PROPERTY_ID = "insert.into.user";
    public static final String DELETE_FROM_USER_PROPERTY_ID = "delete.from.users";
    public static final String UPDATE_USER_PROPERTY_ID = "update.users";
    public static final String SELECT_BY_ID_PROPERTY_ID = "select.user.by.email";
    public static final String SELECT_BY_EMAIL_PROPERTY_ID = "select.user.by.email";
    public static final String SELECT_BY_EMAIL_AND_PASSWORD_ID = "select.user.by.email.and.password";
    public static final String SELECT_BY_PHONENUMBER_ID = "select.user.by.phonenumber";
    public static final String SELECT_BY_PHONENUMBER_AND_PASSWORD_ID = "select.user.by.phonenumber.and.password";
}
