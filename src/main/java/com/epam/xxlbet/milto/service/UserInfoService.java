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
     * Find all user info.
     *
     * @return List of {@link AdminPageUserResponse} java representation of json response body
     */
    List<AdminPageUserResponse> getAllUserInfoForAdminPage();

    /**
     * Update balance of all users that created bet on given match depending on result of the match.
     *
     * @param matchId match id
     * @param matchResultId match result id
     */
    void updateAllUsersBalanceAfterMatchComplete(Long matchId, Long matchResultId);
}
