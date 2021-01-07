package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.StatusDao;
import com.epam.xxlbet.milto.domain.Status;
import com.epam.xxlbet.milto.populator.impl.ResultSetToStatusPopulator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_STATUSES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_STATUS_BY_EMAIL;

/**
 * StatusDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class StatusDaoImpl extends AbstractDao<Status> implements StatusDao {
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
}
