package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.UserDao;
import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.dao.VerificationTokenDao;
import com.epam.xxlbet.milto.dao.impl.UserDaoImpl;
import com.epam.xxlbet.milto.dao.impl.UserInfoDaoImpl;
import com.epam.xxlbet.milto.dao.impl.VerificationTokenDaoImpl;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.domain.VerificationToken;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.populator.impl.RegistrationRequestToUserInfoPopulator;
import com.epam.xxlbet.milto.populator.impl.RegistrationRequestToUserPopulator;
import com.epam.xxlbet.milto.requestbody.RegistrationRequest;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.utils.XxlBetConstants;
import com.epam.xxlbet.milto.utils.cryptography.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class XxlUserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(XxlUserServiceImpl.class);
    private static XxlUserServiceImpl instance;
    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private VerificationTokenDao verificationTokenDao;
    private EmailSender emailSender;
    private Populator<RegistrationRequest, User> registrationToUserPopulator;
    private Populator<RegistrationRequest, UserInfo> registrationToUserInfoPopulator;

    private XxlUserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
        userInfoDao = UserInfoDaoImpl.getInstance();
        verificationTokenDao = VerificationTokenDaoImpl.getInstance();
        registrationToUserPopulator = RegistrationRequestToUserPopulator.getInstance();
        registrationToUserInfoPopulator = RegistrationRequestToUserInfoPopulator.getInstance();
        emailSender = EmailSenderImpl.getInstance();
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

        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(Date.from(new Date().toInstant().plus(1, DAYS)));
        token.setUserId(getUserByEmail(body.getEmail()).getId());
        verificationTokenDao.create(token);

        try {
            emailSender.sendEmail(body.getEmail(), "<h3>Thank you for register!</h3>\n" +
                    "Please follow provided link to confirm registration\n" +
                    "<a href=\"localhost:8080/xxltoken?command=confirm&token=" + token.getToken() + "\">localhost:8080/xxltoken?command=confirm&token=" + token.getToken() + "</a>", "Confirm registration on XXLBet");
        } catch (MessagingException e) {
            LOG.error("Something went wrong during sending email...", e);
        }

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
