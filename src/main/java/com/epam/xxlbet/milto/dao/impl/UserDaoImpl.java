package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.populator.ResultSetPopulator;
import com.epam.xxlbet.milto.populator.impl.ResultSetToUserPopulator;
import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.dao.UserDao;
import com.epam.xxlbet.milto.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static UserDaoImpl instance;
    private ResultSetPopulator<ResultSet, User> populator;

    private UserDaoImpl() {
        super(XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USERS);
        populator = ResultSetToUserPopulator.getInstance();
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }

        return instance;
    }

    @Override
    public User getUserById(final long id) {
        User user = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.SELECT_BY_ID_PROPERTY_ID));

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user = populator.populate(set);
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserById...", e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(final String email) {
        User user = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.SELECT_BY_EMAIL_PROPERTY_ID));

            statement.setString(1, email);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                user = populator.populate(set);
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserByEmail...", e);
        }

        return user;
    }

    @Override
    public User getUserByPhoneNumber(final String phoneNumber) {
        User user = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.SELECT_BY_PHONENUMBER_ID));

            statement.setString(1, phoneNumber);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                populator.populate(set);
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserById...", e);
        }

        return user;
    }

    @Override
    public User getUserByEmailAndPassword(final String email, final String password) {
        User user = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.SELECT_BY_EMAIL_AND_PASSWORD_ID));

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user = populator.populate(set);
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserByEmailAndPassword...", e);
        }

        return user;
    }

    @Override
    public User getUserByPhoneNumberAndPassword(final String phoneNumber, final String password) {
        User user = null;

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.SELECT_BY_PHONENUMBER_AND_PASSWORD_ID));

            statement.setString(1, phoneNumber);
            statement.setString(2, password);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user = populator.populate(set);
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserByPhoneNumberAndPassword...", e);
        }

        return user;
    }

    @Override
    public User createUser(final User user) {
        User newUser = null;

        try(final Connection connection = getConnectionPool().getConnection()){
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.INSERT_INTO_USER_PROPERTY_ID));

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.getEnabled());

            statement.execute();
            statement.close();

            newUser = getUserByEmail(user.getEmail());
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " createUser...", e);
        }

        return newUser;
    }

    @Override
    public void deleteUser(final String email, final String phoneNumber) {
        try(final Connection connection = getConnectionPool().getConnection()){
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.DELETE_FROM_USER_PROPERTY_ID));

            statement.setString(1, email);
            statement.setString(2, phoneNumber);;

            statement.execute();
            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " deleteUser...", e);
        }
    }

    @Override
    public User updateUser(User user) {
        try(final Connection connection = getConnectionPool().getConnection()){
            final PreparedStatement statement = connection.prepareStatement(getSqlById(XxlBetConstants.UPDATE_USER_PROPERTY_ID));

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.getEnabled());
            statement.setLong(5, user.getId());

            statement.executeUpdate();
            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " updateUser...", e);
        }

        return getUserById(user.getId());
    }
}
