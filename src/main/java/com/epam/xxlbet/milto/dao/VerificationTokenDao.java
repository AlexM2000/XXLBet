package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.VerificationToken;

/**
 * VerificationTokenDao.
 *
 * @author Aliaksei Milto
 */
public interface VerificationTokenDao {

    /**
     * Create verification token in database.
     *
     * @param token {@link VerificationToken}
     * @return Created {@link VerificationToken} (taken from database)
     */
    VerificationToken create(VerificationToken token);

    /**
     * Find {@link VerificationToken} by token.
     *
     * @param token token
     * @return {@link VerificationToken}
     */
    VerificationToken getVerificationTokenByToken(String token);

    /**
     * Delete {@link VerificationToken} belonged to given user
     *
     * @param userId user id
     */
    void deleteVerificationToken(Long userId);
}
