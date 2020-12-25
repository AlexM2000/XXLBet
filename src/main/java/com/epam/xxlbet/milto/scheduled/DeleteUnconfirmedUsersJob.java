package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;

/**
 * Deletes users that didn't confirm registration.
 *
 * @author Aliaksei Milto
 */
public class DeleteUnconfirmedUsersJob implements Runnable {
    private static DeleteUnconfirmedUsersJob instance;
    private UserService userService;

    private DeleteUnconfirmedUsersJob() {
        userService = UserServiceImpl.getInstance();
    }

    public static DeleteUnconfirmedUsersJob getInstance() {
        if (instance == null) {
            instance = new DeleteUnconfirmedUsersJob();
        }

        return instance;
    }

    @Override
    public void run() {
        userService.deleteAllUnconfirmedUsers();
    }
}
