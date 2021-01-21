package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToPasswordChangeRequestPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToPasswordChangeRequestPopulator
        implements ResultSetPopulator<PasswordChangeRequest> {

    private static final String ID = "id";
    private static final String TOKEN = "token";
    private static final String EXPIRES_AT = "expires_at";
    private static final String USER_ID = "user_id";
    private static ResultSetToPasswordChangeRequestPopulator instance;

    private ResultSetToPasswordChangeRequestPopulator() { }

    public static ResultSetToPasswordChangeRequestPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToPasswordChangeRequestPopulator();
        }

        return instance;
    }

    @Override
    public PasswordChangeRequest populate(ResultSet source) throws SQLException {
        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setId(source.getLong(ID));
        request.setUserId(source.getLong(USER_ID));
        request.setToken(source.getString(TOKEN));
        request.setExpiresAt(source.getTimestamp(EXPIRES_AT).toLocalDateTime());
        return request;
    }
}
