package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.VerificationToken;

public interface VerificationTokenService {
    VerificationToken getToken(String token);
    void deleteAllExpiredTokens();
    VerificationToken createToken(Long userId);
    void deleteUserToken(Long userId);
}
