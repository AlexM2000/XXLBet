package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.PasswordChangeRequestDao;
import com.epam.xxlbet.milto.dao.impl.PasswordChangeRequestDaoImpl;
import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.service.PasswordChangeRequestService;
import com.epam.xxlbet.milto.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * PasswordChangeRequestServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class PasswordChangeRequestServiceImpl implements PasswordChangeRequestService {
    private static PasswordChangeRequestServiceImpl instance;
    private UserService userService;
    private PasswordChangeRequestDao passwordChangeRequestDao;

    private PasswordChangeRequestServiceImpl() {
        this.userService = UserServiceImpl.getInstance();
        this.passwordChangeRequestDao = PasswordChangeRequestDaoImpl.getInstance();
    }

    public static PasswordChangeRequestServiceImpl getInstance() {
        if (instance == null) {
            instance = new PasswordChangeRequestServiceImpl();
        }

        return instance;
    }

    @Override
    public PasswordChangeRequest createPasswordChangeRequest(String email) {
        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setExpiresAt(LocalDateTime.now().plusDays(1L));
        request.setToken(UUID.randomUUID().toString());
        request.setUserId(userService.getUserByEmail(email).getId());

        passwordChangeRequestDao.createPasswordChangeRequest(request);

        return findPasswordChangeRequestByToken(request.getToken());
    }

    @Override
    public PasswordChangeRequest findPasswordChangeRequestByToken(String token) {
        return passwordChangeRequestDao.findPasswordChangeRequestByToken(token);
    }

    @Override
    public void deletePasswordChangeRequest(String token) {
        passwordChangeRequestDao.deletePasswordChangeRequest(token);
    }

    @Override
    public void deleteAllExpiredPasswordChangeRequests() {
        passwordChangeRequestDao.deleteAllExpiredPasswordChangeRequests();
    }
}
