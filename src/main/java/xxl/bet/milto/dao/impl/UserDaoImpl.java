package xxl.bet.milto.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.dao.UserDao;
import xxl.bet.milto.domain.User;
import xxl.bet.milto.requestbody.RegistrationRequest;
import xxl.bet.milto.utils.PropertyLoader;
import xxl.bet.milto.utils.connection.ConnectionPool;

import java.io.IOException;

import static xxl.bet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USERS;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance;
    private ConnectionPool connectionPool;

    private UserDaoImpl() {
        try {
            PropertyLoader.getInstance().init(FILE_WITH_QUERIES_FOR_TABLE_USERS);
        } catch (IOException e) {
            LOG.error("Could not load queries for database! Exiting...");
            System.exit(1);
        }

        connectionPool = ConnectionPool.getInstance();
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }

        return instance;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        return null;
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
