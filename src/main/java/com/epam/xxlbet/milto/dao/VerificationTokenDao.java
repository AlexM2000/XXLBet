package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.VerificationToken;

public interface VerificationTokenDao {
    VerificationToken create(VerificationToken token);
    VerificationToken getVerificationTokenByToken(String token);
    void deleteAllExpiredTokens();
    void deleteVerificationToken(Long userId);
}
