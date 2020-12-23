package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetToUserInfoPopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToUserInfoPopulator implements ResultSetPopulator<ResultSet, UserInfo> {
    private static final String EMAIL_COLUMN_NAME = "email";
    private static final String PHONE_NUMBER_COLUMN_NAME = "phone_number";
    private static final String SURNAME_COLUMN_NAME = "surname";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String SECOND_NAME_COLUMN_NAME = "second_name";
    private static final String BIRTH_DATE_COLUMN_NAME = "birth_date";
    private static final String REGISTRATION_DATE_COLUMN_NAME = "registration_date";
    private static final String BALANCE_COLUMN_NAME = "balance";
    private static final String PROFILE_IMG_PATH_COLUMN_NAME = "profile_img_path";
    private static final String STATUS_ID_COLUMN_NAME = "status_id";
    private static final String ROLE_ID_COLUMN_NAME = "role_id";

    private static ResultSetToUserInfoPopulator instance;

    private ResultSetToUserInfoPopulator() { }

    public static ResultSetToUserInfoPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToUserInfoPopulator();
        }

        return instance;
    }

    @Override
    public UserInfo populate(ResultSet resultSet) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(resultSet.getString(EMAIL_COLUMN_NAME));
        userInfo.setPhoneNumber(resultSet.getString(PHONE_NUMBER_COLUMN_NAME));
        userInfo.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
        userInfo.setName(resultSet.getString(NAME_COLUMN_NAME));
        userInfo.setSecondName(resultSet.getString(SECOND_NAME_COLUMN_NAME));
        userInfo.setBirthDate(resultSet.getDate(BIRTH_DATE_COLUMN_NAME));
        userInfo.setRegistrationDate(resultSet.getTimestamp(REGISTRATION_DATE_COLUMN_NAME).toLocalDateTime());
        userInfo.setBalance(resultSet.getBigDecimal(BALANCE_COLUMN_NAME));
        userInfo.setProfileImgPath(resultSet.getString(PROFILE_IMG_PATH_COLUMN_NAME));
        userInfo.setStatusId(resultSet.getLong(STATUS_ID_COLUMN_NAME));
        userInfo.setRoleId(resultSet.getLong(ROLE_ID_COLUMN_NAME));
        return userInfo;
    }
}
