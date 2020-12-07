package xxl.bet.milto.dao.impl;

import xxl.bet.milto.dao.UserDao;
import xxl.bet.milto.domain.User;
import xxl.bet.milto.requestbody.RegistrationRequest;

import static xxl.bet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USERS;

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
