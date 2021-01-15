package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.UserInfo;

import java.util.List;

/**
 * UserInfoDao.
 *
 * @author Aliaksei Milto
 */
public interface UserInfoDao {

    /**
     * Create user info in database.
     *
     * @param userInfo {@link UserInfo}
     * @return Created {@link UserInfo} (taken from database)
     */
    UserInfo createNewUserInfo(UserInfo userInfo);

    /**
     * Update user info in database.
     *
     * @param userInfo {@link UserInfo} with updated data
     * @return Updated {@link UserInfo} (taken from database)
     */
    UserInfo updateUserInfo(UserInfo userInfo);

    /**
     * Find user info by user email.
     *
     * @param email user email
     * @return {@link UserInfo}
     */
    UserInfo getUserInfoByEmail(String email);

    /**
     * Find all user info.
     *
     * @return all user info
     */
    List<UserInfo> getAllUsers();

    /**
     * Update balance of all users that created bet on given match depending on result of the match.
     *
     * @param matchId match id
     * @param matchResultId match result id
     */
    void updateAllUsersBalanceAfterMatchComplete(Long matchId, Long matchResultId);
}
