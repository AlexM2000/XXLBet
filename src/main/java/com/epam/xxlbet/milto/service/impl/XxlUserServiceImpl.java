package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.dao.impl.UserDaoImpl;
import com.epam.xxlbet.milto.dao.impl.UserInfoDaoImpl;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.populator.impl.RegistrationRequestToUserInfoPopulator;
import com.epam.xxlbet.milto.populator.impl.RegistrationRequestToUserPopulator;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.utils.cryptography.CryptoUtils;
import com.epam.xxlbet.milto.dao.UserDao;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class XxlUserServiceImpl implements UserService {
    private static XxlUserServiceImpl instance;
    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private Populator<RegistrationRequest, User> registrationToUserPopulator;
    private Populator<RegistrationRequest, UserInfo> registrationToUserInfoPopulator;

    private XxlUserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
        userInfoDao = UserInfoDaoImpl.getInstance();
        registrationToUserPopulator = RegistrationRequestToUserPopulator.getInstance();
        registrationToUserInfoPopulator = RegistrationRequestToUserInfoPopulator.getInstance();
    }

    public static XxlUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new XxlUserServiceImpl();
        }

        return instance;
    }

    @Override
    public User createNewUser(final RegistrationRequest body) {
        String encryptedPassword = CryptoUtils.encrypt(body.getPassword(), PropertyLoader.getInstance().getStringProperty(XxlBetConstants.PROJECT_PROPERTIES, XxlBetConstants.SECRET_KEY)
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
    public User getUserByEmail(final String email) {
        return userDao.getUserByEmail(email);
    }
}
