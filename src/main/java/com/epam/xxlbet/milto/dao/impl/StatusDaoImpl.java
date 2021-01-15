package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.StatusDao;
import com.epam.xxlbet.milto.domain.Status;
import com.epam.xxlbet.milto.populator.impl.ResultSetToStatusPopulator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_STATUSES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_STATUS_BY_EMAIL;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_STATUS_BY_ID;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_STATUS_BY_NAME;

/**
 * StatusDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class StatusDaoImpl extends AbstractDaoImpl<Status> implements StatusDao {
    private static StatusDaoImpl instance;

    private StatusDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_STATUSES, ResultSetToStatusPopulator.getInstance());
    }

    public static StatusDaoImpl getInstance() {
        if (instance == null) {
            instance = new StatusDaoImpl();
        }

        return instance;
    }

    @Override
    public Status getUserStatusByEmail(String email) {
        return executeForSingleResult(SELECT_USER_STATUS_BY_EMAIL, email);
    }

    @Override
    public Status getStatusById(Long statusId) {
        return executeForSingleResult(SELECT_USER_STATUS_BY_ID, statusId);
    }

    @Override
    public Status getStatusByName(String name) {
        return executeForSingleResult(SELECT_USER_STATUS_BY_NAME, name);
    }
}
