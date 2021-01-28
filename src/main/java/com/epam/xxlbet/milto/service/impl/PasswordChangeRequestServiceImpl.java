package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.PasswordChangeRequestDao;
import com.epam.xxlbet.milto.dao.impl.PasswordChangeRequestDaoImpl;
import com.epam.xxlbet.milto.domain.ConfirmationResult;
import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.requestandresponsebody.ChangePasswordRequest;
import com.epam.xxlbet.milto.service.PasswordChangeRequestService;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.utils.XxlBetConstants;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;
import static com.epam.xxlbet.milto.utils.cryptography.CryptoUtils.encrypt;

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

    @Override
    public void changePassword(ChangePasswordRequest passwordRequest) {
        User user = userService.getUserById(passwordRequest.getUserId());

        user.setPassword(getEncryptedPassword(passwordRequest.getPassword()));

        userService.updateUser(user);

        passwordChangeRequestDao.deletePasswordChangeRequest(passwordRequest.getUserId());
    }

    /**
     * Encrypt password using {@link com.epam.xxlbet.milto.utils.cryptography.CryptoUtils}
     *
     * @param password password
     * @return encrypted password
     */
    private String getEncryptedPassword(String password) {
        return encrypt(password,
                PropertyLoader.getInstance().getStringProperty(PROJECT_PROPERTIES, XxlBetConstants.SECRET_KEY)
                        .orElseThrow(() -> new PropertyNotFoundException("Can't find secret key property!"))
        );
    }
}
