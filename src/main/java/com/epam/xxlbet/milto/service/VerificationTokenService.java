package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.VerificationToken;

/**
 * VerificationTokenService.
 *
 * @author Aliaksei Milto
 */
public interface VerificationTokenService {
    VerificationToken getToken(String token);
    VerificationToken createToken(Long userId);
    void deleteUserToken(Long userId);
}
