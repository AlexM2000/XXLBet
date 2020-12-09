package xxl.bet.milto.dao.impl;

import xxl.bet.milto.dao.UserDao;
import xxl.bet.milto.domain.User;
import xxl.bet.milto.requestbody.RegistrationRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static xxl.bet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USERS;
import static xxl.bet.milto.utils.XxlBetConstants.SELECT_BY_EMAIL_AND_PASSWORD_ID;
import static xxl.bet.milto.utils.XxlBetConstants.SELECT_BY_EMAIL_PROPERTY_ID;
import static xxl.bet.milto.utils.XxlBetConstants.SELECT_BY_ID_PROPERTY_ID;
import static xxl.bet.milto.utils.XxlBetConstants.SELECT_BY_PHONENUMBER_AND_PASSWORD_ID;
import static xxl.bet.milto.utils.XxlBetConstants.SELECT_BY_PHONENUMBER_ID;

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static UserDaoImpl instance;

    private UserDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_USERS);
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }

        return instance;
    }

    @Override
    public User getUserById(final long id) {
        User user = new User();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSql(FILE_WITH_QUERIES_FOR_TABLE_USERS, SELECT_BY_ID_PROPERTY_ID));

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user.setId(set.getLong("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setPhoneNumber(set.getString("phone_number"));
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserById...", e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(final String email) {
        User user = new User();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSql(FILE_WITH_QUERIES_FOR_TABLE_USERS, SELECT_BY_EMAIL_PROPERTY_ID));

            statement.setString(1, email);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                user.setId(set.getLong("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setPhoneNumber(set.getString("phone_number"));
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserByEmail...", e);
        }

        return user;
    }

    @Override
    public User getUserByPhoneNumber(final String phoneNumber) {
        User user = new User();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSql(FILE_WITH_QUERIES_FOR_TABLE_USERS, SELECT_BY_PHONENUMBER_ID));

            statement.setString(1, phoneNumber);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user.setId(set.getLong("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setPhoneNumber(set.getString("phone_number"));
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserById...", e);
        }

        return user;
    }

    @Override
    public User getUserByEmailAndPassword(final String email, final String password) {
        User user = new User();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSql(FILE_WITH_QUERIES_FOR_TABLE_USERS, SELECT_BY_EMAIL_AND_PASSWORD_ID));

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user.setId(set.getLong("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setPhoneNumber(set.getString("phone_number"));
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserByEmailAndPassword...", e);
        }

        return user;
    }

    @Override
    public User getUserByPhoneNumberAndPassword(final String phoneNumber, final String password) {
        User user = new User();

        try(final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSql(FILE_WITH_QUERIES_FOR_TABLE_USERS, SELECT_BY_PHONENUMBER_AND_PASSWORD_ID));

            statement.setString(1, phoneNumber);
            statement.setString(2, password);

            ResultSet set = statement.executeQuery();


            while (set.next()) {
                user.setId(set.getLong("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setPhoneNumber(set.getString("phone_number"));
            }

            statement.close();
        } catch (final InterruptedException | SQLException e) {
            getLogger().error(getErrorMsgBegin() + " getUserByPhoneNumberAndPassword...", e);
        }

        return user;
    }

    @Override
    public User createUser(RegistrationRequest body) {
        return null;
    }

    @Override
    public User deleteUser(String email, String phoneNumber) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
