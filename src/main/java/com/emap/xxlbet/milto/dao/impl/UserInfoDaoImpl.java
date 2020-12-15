package com.emap.xxlbet.milto.dao.impl;

import com.emap.xxlbet.milto.dao.UserInfoDao;
import com.emap.xxlbet.milto.utils.XxlBetConstants;
import com.emap.xxlbet.milto.domain.UserInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInfoDaoImpl extends AbstractDao implements UserInfoDao {
    private UserInfoDaoImpl() {
        super(XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USER_INFO);
    }

    @Override
    public UserInfo createNewUserInfo(UserInfo userInfo) {
        UserInfo newUserInfo = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.INSERT_INTO_USER_INFO));

            // email, phone_number, surname, name, second_name, birth_date)
            statement.setString(1, userInfo.getEmail());
            statement.setString(2, userInfo.getPhoneNumber());
            statement.setString(3, userInfo.getSurname());
            statement.setString(4, userInfo.getName());
            statement.setString(5, userInfo.getSecondName());
            statement.setDate(6, new Date(userInfo.getBirthDate().getTime()));

            statement.execute();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error("Something went wrong while executing createNewUserInfo...", e);
        }
    }
}
