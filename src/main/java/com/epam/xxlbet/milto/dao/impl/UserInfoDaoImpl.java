package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;
import com.epam.xxlbet.milto.populator.impl.ResultSetToUserInfoPopulator;
import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.domain.UserInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_USER_INFO;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_INFO_BY_EMAIL;

public class UserInfoDaoImpl extends AbstractDao implements UserInfoDao {
    private static UserInfoDaoImpl instance;
    private ResultSetPopulator<ResultSet, UserInfo> populator;

    private UserInfoDaoImpl() {
        super(XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USER_INFO);
        populator = ResultSetToUserInfoPopulator.getInstance();
    }

    public static UserInfoDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserInfoDaoImpl();
        }

        return instance;
    }

    @Override
    public UserInfo createNewUserInfo(UserInfo userInfo) {
        UserInfo newUserInfo = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(INSERT_INTO_USER_INFO));

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

        return newUserInfo;
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        UserInfo userInfo = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(SELECT_USER_INFO_BY_EMAIL));

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userInfo = populator.populate(resultSet);
            }
        } catch (final InterruptedException | SQLException e) {
            getLogger().error("Something went wrong while executing getUserInfoByEmail...", e);
        }

        return userInfo;
    }
}
