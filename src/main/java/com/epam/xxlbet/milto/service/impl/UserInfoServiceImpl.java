package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.dao.impl.UserInfoDaoImpl;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.service.UserInfoService;

/**
 * UserInfoServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class UserInfoServiceImpl implements UserInfoService {
    private static UserInfoServiceImpl instance;
    private UserInfoDao userInfoDao;

    private UserInfoServiceImpl() {
        userInfoDao = UserInfoDaoImpl.getInstance();
    }

    public static UserInfoServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserInfoServiceImpl();
        }

        return instance;
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        return userInfoDao.getUserInfoByEmail(email);
    }
}
