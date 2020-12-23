package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.UserDao;
import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.dao.impl.UserDaoImpl;
import com.epam.xxlbet.milto.dao.impl.UserInfoDaoImpl;
import com.epam.xxlbet.milto.domain.ConfirmationResult;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.domain.VerificationToken;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.populator.impl.RegistrationRequestToUserInfoPopulator;
import com.epam.xxlbet.milto.populator.impl.RegistrationRequestToUserPopulator;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.VerificationTokenService;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.utils.cryptography.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;

import static com.epam.xxlbet.milto.domain.ConfirmationResult.EXPIRED;
import static com.epam.xxlbet.milto.domain.ConfirmationResult.INVALID;
import static com.epam.xxlbet.milto.domain.ConfirmationResult.SUCCESS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * UserServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private static UserServiceImpl instance;
    private VerificationTokenService verificationTokenService;
    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private Populator<RegistrationRequest, User> registrationToUserPopulator;
    private Populator<RegistrationRequest, UserInfo> registrationToUserInfoPopulator;

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
        userInfoDao = UserInfoDaoImpl.getInstance();
        verificationTokenService = VerificationTokenServiceImpl.getInstance();
        registrationToUserPopulator = RegistrationRequestToUserPopulator.getInstance();
        registrationToUserInfoPopulator = RegistrationRequestToUserInfoPopulator.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }

    @Override
    public User createNewUser(final RegistrationRequest body) {
        String encryptedPassword = CryptoUtils.encrypt(body.getPassword(), PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, XxlBetConstants.SECRET_KEY)
                .orElseThrow(() -> new PropertyNotFoundException("Can't find secret key property!")));

        User user = new User();
        user.setPassword(encryptedPassword);
        registrationToUserPopulator.populate(body, user);
        user.setEnabled(false);
        userDao.createUser(user);

        UserInfo userInfo = new UserInfo();
        registrationToUserInfoPopulator.populate(body, userInfo);
        userInfo.setBalance(new BigDecimal(0));
        userInfoDao.createNewUserInfo(userInfo);

        return userDao.getUserByEmail(body.getEmail());
    }

    @Override
    public boolean checkIfUserExists(String email) {
        User user = userDao.getUserByEmail(email);

        return user != null && !isBlank(user.getEmail());
    }

    @Override
    public boolean checkIfUserExistsByPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public User getUserByEmail(final String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        String encryptedPassword = CryptoUtils.encrypt(password, PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, XxlBetConstants.SECRET_KEY)
                .orElseThrow(() -> new PropertyNotFoundException("Can't find secret key property!")));

        return userDao.getUserByEmailAndPassword(email, encryptedPassword);
    }

    @Override
    public ConfirmationResult confirmRegistration(String token) {
        ConfirmationResult result;
        VerificationToken verificationToken = verificationTokenService.getToken(token);

        if (verificationToken != null) {
            if (verificationToken.getExpiresAt().after(new Date())) {
                User user = userDao.getUserById(verificationToken.getUserId());
                user.setEnabled(true);
                userDao.updateUser(user);

                result = SUCCESS;
            } else {
                result = EXPIRED;
            }

            verificationTokenService.deleteUserToken(verificationToken.getUserId());
        } else {
            result = INVALID;
        }

        return result;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
