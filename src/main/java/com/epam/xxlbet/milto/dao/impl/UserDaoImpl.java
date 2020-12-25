package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.UserDao;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.populator.impl.ResultSetToUserPopulator;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_ALL_UNCONFIRMED_USERS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_FROM_USER_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USERS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_USER_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BY_EMAIL_AND_PASSWORD_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BY_EMAIL_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BY_ID_PROPERTY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BY_PHONENUMBER_AND_PASSWORD_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_BY_PHONENUMBER_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_UNCONFIRMED_USERS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.UPDATE_USER_PROPERTY_ID;

/**
 * UserDaoImpl.
 *
 * @author alexm2000
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static UserDaoImpl instance;

    private UserDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_USERS, ResultSetToUserPopulator.getInstance());
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }

        return instance;
    }

    @Override
    public User getUserById(final long id) {
        return executeForSingleResult(SELECT_BY_ID_PROPERTY_ID, id);
    }

    @Override
    public User getUserByEmail(final String email) {
        return executeForSingleResult(SELECT_BY_EMAIL_PROPERTY_ID, email);
    }

    @Override
    public User getUserByPhoneNumber(final String phoneNumber) {
        return executeForSingleResult(SELECT_BY_PHONENUMBER_ID, phoneNumber);
    }

    @Override
    public User getUserByEmailAndPassword(final String email, final String password) {
        return executeForSingleResult(SELECT_BY_EMAIL_AND_PASSWORD_ID, email, password);
    }

    @Override
    public User getUserByPhoneNumberAndPassword(final String phoneNumber, final String password) {
        return executeForSingleResult(SELECT_BY_PHONENUMBER_AND_PASSWORD_ID, phoneNumber, password);
    }

    @Override
    public User createUser(final User user) {
        execute(INSERT_INTO_USER_PROPERTY_ID, user.getEmail(), user.getPhoneNumber(), user.getPassword(), user.getEnabled());
        return getUserByEmail(user.getEmail());
    }

    @Override
    public void deleteUser(final String email, final String phoneNumber) {
        executeUpdate(DELETE_FROM_USER_PROPERTY_ID, email, phoneNumber);
    }

    @Override
    public User updateUser(User user) {
        executeUpdate(UPDATE_USER_PROPERTY_ID, user.getEmail(), user.getPhoneNumber(), user.getPassword(), user.getEnabled(), user.getId());
        return getUserById(user.getId());
    }

    @Override
    public void deleteAllUnconfirmedUsers() {
        executeUpdate(DELETE_ALL_UNCONFIRMED_USERS);
    }

    @Override
    public List<User> getAllUnconfirmedUsers() {
        return executeQuery(SELECT_UNCONFIRMED_USERS);
    }
}
