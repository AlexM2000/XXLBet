package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.service.PasswordChangeRequestService;
import com.epam.xxlbet.milto.service.impl.PasswordChangeRequestServiceImpl;

/**
 * DeleteExpiredPasswordRequestsJob.
 * Delete expired change password requests.
 *
 * @author Aliaksei Milto
 */
public class DeleteExpiredPasswordRequestsJob implements Runnable {
    private static DeleteExpiredPasswordRequestsJob instance;
    private PasswordChangeRequestService service;

    private DeleteExpiredPasswordRequestsJob() {
        this.service = PasswordChangeRequestServiceImpl.getInstance();
    }

    public static DeleteExpiredPasswordRequestsJob getInstance() {
        if (instance == null) {
            instance = new DeleteExpiredPasswordRequestsJob();
        }

        return instance;
    }

    @Override
    public void run() {
        service.deleteAllExpiredPasswordChangeRequests();
    }
}
