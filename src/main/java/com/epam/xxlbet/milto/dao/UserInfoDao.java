package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.UserInfo;

/**
 * UserInfoDao.
 *
 * @author Aliaksei Milto
 */
public interface UserInfoDao {
    UserInfo createNewUserInfo(UserInfo userInfo);
    UserInfo getUserInfoByEmail(String email);

}
