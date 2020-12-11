package xxl.bet.milto.service.impl;

import xxl.bet.milto.dao.UserDao;
import xxl.bet.milto.dao.impl.UserDaoImpl;
import xxl.bet.milto.domain.User;
import xxl.bet.milto.exceptions.PropertyNotFoundException;
import xxl.bet.milto.service.UserService;
import xxl.bet.milto.utils.PropertyLoader;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static xxl.bet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;
import static xxl.bet.milto.utils.XxlBetConstants.SECRET_KEY;
import static xxl.bet.milto.utils.cryptography.CryptoUtils.encrypt;

public class XxlUserServiceImpl implements UserService {
    private static XxlUserServiceImpl instance;
    private UserDao userDao;

    private XxlUserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }

    public static XxlUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new XxlUserServiceImpl();
        }

        return instance;
    }

    @Override
    public User createUser(String email, String phoneNumber, String password) {
        String encryptedPassword = encrypt(password, PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, SECRET_KEY)
                .orElseThrow(() -> new PropertyNotFoundException("Can't find secret key property!")));

        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setPhoneNumber(phoneNumber);

        return userDao.createUser(user);
    }

    @Override
    public boolean checkIfUserExists(String email) {
        User user = userDao.getUserByEmail(email);

        return user != null && !isBlank(user.getEmail());
    }
}
