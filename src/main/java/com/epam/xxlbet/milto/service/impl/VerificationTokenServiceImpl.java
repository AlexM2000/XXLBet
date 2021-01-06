package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.VerificationTokenDao;
import com.epam.xxlbet.milto.dao.impl.VerificationTokenDaoImpl;
import com.epam.xxlbet.milto.domain.VerificationToken;
import com.epam.xxlbet.milto.service.VerificationTokenService;

import java.util.Date;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * VerificationTokenServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private static VerificationTokenServiceImpl instance;
    private VerificationTokenDao verificationTokenDao;

    private VerificationTokenServiceImpl() {
        this.verificationTokenDao = VerificationTokenDaoImpl.getInstance();
    }

    public static VerificationTokenServiceImpl getInstance() {
        if (instance == null) {
            instance = new VerificationTokenServiceImpl();
        }

        return instance;
    }

    @Override
    public VerificationToken getToken(final String token) {
        return verificationTokenDao.getVerificationTokenByToken(token);
    }

    @Override
    public VerificationToken createToken(final Long userId) {
        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(Date.from(new Date().toInstant().plus(1, DAYS)));
        token.setUserId(userId);
        return verificationTokenDao.create(token);
    }

    @Override
    public void deleteUserToken(Long userId) {
        verificationTokenDao.deleteVerificationToken(userId);
    }
}
