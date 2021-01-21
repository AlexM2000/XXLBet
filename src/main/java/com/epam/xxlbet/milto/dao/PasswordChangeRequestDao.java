package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.PasswordChangeRequest;

/**
 * PasswordChangeRequestDao.
 *
 * @author Aliaksei Milto
 */
public interface PasswordChangeRequestDao {

    /**
     * Create password change request in database.
     *
     * @param request PasswordChangeRequest
     * @return Created PasswordChangeRequest from database
     */
    PasswordChangeRequest createPasswordChangeRequest(PasswordChangeRequest request);

    /**
     * Find password change request by token.
     *
     * @param token token
     * @return password change request
     */
    PasswordChangeRequest findPasswordChangeRequestByToken(String token);

    /**
     * Delete password change request by token.
     *
     * @param token token
     */
    void deletePasswordChangeRequest(String token);

    /**
     * Delete password change request by user.
     *
     * @param userId user id.
     */
    void deletePasswordChangeRequest(Long userId);

    /**
     * Delete all expired password change requests.
     */
    void deleteAllExpiredPasswordChangeRequests();
}
