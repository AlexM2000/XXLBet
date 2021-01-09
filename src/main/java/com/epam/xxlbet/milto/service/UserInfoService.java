package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.requestandresponsebody.AdminPageUserResponse;

import java.util.List;

/**
 * UserInfoService.
 *
 * @author Aliaksei Milto
 */
public interface UserInfoService {
    UserInfo getUserInfoByEmail(String email);
    UserInfo createNewUserInfo(UserInfo userInfo);
    UserInfo updateUserInfo(UserInfo userInfo);
    List<AdminPageUserResponse> getAllUserInfoForAdminPage();
}
