package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.requestandresponsebody.BookmakerPageUserResponse;

import java.util.List;

/**
 * UserInfoService.
 *
 * @author Aliaksei Milto
 */
public interface UserInfoService {

    /**
     * Find user info by user email
     *
     * @param email user email
     * @return user info
     */
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
     * @return List of {@link BookmakerPageUserResponse} java representation of json response body
     */
    List<BookmakerPageUserResponse> getAllUserInfoForAdminPage();

    /**
     * Update balance of all users that created bet on given match depending on result of the match.
     *
     * @param matchId match id
     * @param matchResultId match result id
     */
    void updateAllUsersBalanceAfterMatchComplete(Long matchId, Long matchResultId);
}
