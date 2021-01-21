package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.PasswordChangeRequestDao;
import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.populator.impl.ResultSetToPasswordChangeRequestPopulator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_ALL_EXPIRED_PASSWORD_CHANGE_REQUESTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_PASSWORD_CHANGE_REQUEST_BY_TOKEN;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.DELETE_PASSWORD_CHANGE_REQUEST_BY_USER;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_PASSWORD_CHANGE_REQUESTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_PASSWORD_CHANGE_REQUESTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_PASSWORD_CHANGE_REQUEST_BY_TOKEN;

/**
 * PasswordChangeRequestDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class PasswordChangeRequestDaoImpl
        extends AbstractDaoImpl<PasswordChangeRequest>
        implements PasswordChangeRequestDao {

    private static PasswordChangeRequestDaoImpl instance;

    private PasswordChangeRequestDaoImpl() {
        super(
                FILE_WITH_QUERIES_FOR_TABLE_PASSWORD_CHANGE_REQUESTS,
                ResultSetToPasswordChangeRequestPopulator.getInstance()
        );
    }

    public static PasswordChangeRequestDaoImpl getInstance() {
        if (instance == null) {
            instance = new PasswordChangeRequestDaoImpl();
        }

        return instance;
    }


    @Override
    public PasswordChangeRequest createPasswordChangeRequest(PasswordChangeRequest request) {
        executeUpdate(
                INSERT_INTO_PASSWORD_CHANGE_REQUESTS,
                request.getToken(),
                request.getExpiresAt(),
                request.getUserId()
        );

        return findPasswordChangeRequestByToken(request.getToken());
    }

    @Override
    public PasswordChangeRequest findPasswordChangeRequestByToken(String token) {
        return executeForSingleResult(
                SELECT_PASSWORD_CHANGE_REQUEST_BY_TOKEN,
                token
        );
    }

    @Override
    public void deletePasswordChangeRequest(String token) {
        executeUpdate(
                DELETE_PASSWORD_CHANGE_REQUEST_BY_TOKEN,
                token
        );
    }

    @Override
    public void deletePasswordChangeRequest(Long userId) {
        executeUpdate(DELETE_PASSWORD_CHANGE_REQUEST_BY_USER, userId);
    }

    @Override
    public void deleteAllExpiredPasswordChangeRequests() {
        executeUpdate(DELETE_ALL_EXPIRED_PASSWORD_CHANGE_REQUESTS);
    }
}
