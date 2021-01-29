package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.VerificationToken;

/**
 * VerificationTokenService.
 *
 * @author Aliaksei Milto
 */
public interface VerificationTokenService {

    /**
     * Find {@link VerificationToken} by token.
     *
     * @param token token
     * @return {@link VerificationToken}
     */
    VerificationToken getToken(String token);

    /**
     * Create verification token for user with given id.
     *
     * @param userId user id
     * @return Created {@link VerificationToken} (taken from database)
     */
    VerificationToken createToken(Long userId);

    /**
     * Delete {@link VerificationToken} belonged to given user.
     *
     * @param userId user id
     */
    void deleteUserToken(Long userId);
}
