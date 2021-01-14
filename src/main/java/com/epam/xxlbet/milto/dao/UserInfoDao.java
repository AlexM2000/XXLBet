package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.UserInfo;

import java.util.List;

/**
 * UserInfoDao.
 *
 * @author Aliaksei Milto
 */
public interface UserInfoDao {
    UserInfo createNewUserInfo(UserInfo userInfo);
    UserInfo updateUserInfo(UserInfo userInfo);
    UserInfo getUserInfoByEmail(String email);
    List<UserInfo> getAllUsers();
    void updateAllUsersBalanceAfterMatchComplete(Long matchId, Long matchResultId);
}
