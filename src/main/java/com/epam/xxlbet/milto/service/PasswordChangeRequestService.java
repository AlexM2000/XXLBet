package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.requestandresponsebody.ChangePasswordRequest;

/**
 * PasswordChangeRequestService.
 *
 * @author Aliaksei Milto
 */
public interface PasswordChangeRequestService {

    /**
     * Create password change request and send email for changing password.
     *
     * @param email user email
     * @return Created {@link PasswordChangeRequest} (taken from database)
     */
    PasswordChangeRequest createPasswordChangeRequest(String email);

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
     * Delete all expired password change requests.
     */
    void deleteAllExpiredPasswordChangeRequests();

    /**
     * Change user password.
     * All implementations must encrypt password with {@link com.epam.xxlbet.milto.utils.cryptography.CryptoUtils}
     * before adding to database.
     *
     * @param passwordRequest change password request
     */
     void changePassword(ChangePasswordRequest passwordRequest);
}
