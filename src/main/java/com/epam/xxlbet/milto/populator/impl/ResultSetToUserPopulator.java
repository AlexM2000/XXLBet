package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToUserPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToUserPopulator implements ResultSetPopulator<User> {
    private static final String ID_COLUMN_NAME = "id";
    private static final String EMAIL_COLUMN_NAME = "email";
    private static final String PASSWORD_COLUMN_NAME = "password";
    private static final String PHONE_NUMBER_COLUMN_NAME = "phone_number";
    private static final String ENABLED_COLUMN_NAME = "enabled";

    private static ResultSetToUserPopulator instance;

    private ResultSetToUserPopulator() { }

    public static ResultSetToUserPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToUserPopulator();
        }

        return instance;
    }

    @Override
    public User populate(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID_COLUMN_NAME));
        user.setEmail(resultSet.getString(EMAIL_COLUMN_NAME));
        user.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
        user.setPhoneNumber(resultSet.getString(PHONE_NUMBER_COLUMN_NAME));
        user.setEnabled(resultSet.getBoolean(ENABLED_COLUMN_NAME));
        return user;
    }
}
