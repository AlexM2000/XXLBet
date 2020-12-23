package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.service.VerificationTokenService;
import com.epam.xxlbet.milto.service.impl.VerificationTokenServiceImpl;

public class DeleteOutdatedVerificationTokensJob implements Runnable {
    private static DeleteOutdatedVerificationTokensJob instance;
    private VerificationTokenService service;

    private DeleteOutdatedVerificationTokensJob() {
        service = VerificationTokenServiceImpl.getInstance();
    }

    public static DeleteOutdatedVerificationTokensJob getInstance() {
        if (instance == null) {
            instance = new DeleteOutdatedVerificationTokensJob();
        }

        return instance;
    }

    @Override
    public void run() {
        service.deleteAllExpiredTokens();
    }
}
