package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.UserInfo;

/**
 * UserInfoService.
 *
 * @author Aliaksei Milto
 */
public interface UserInfoService {
    UserInfo getUserInfoByEmail(String email);
    UserInfo createNewUserInfo(UserInfo userInfo);
}
