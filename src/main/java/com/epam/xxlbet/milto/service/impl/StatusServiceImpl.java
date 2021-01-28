package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.StatusDao;
import com.epam.xxlbet.milto.dao.impl.StatusDaoImpl;
import com.epam.xxlbet.milto.domain.Status;
import com.epam.xxlbet.milto.service.StatusService;

/**
 * StatusServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class StatusServiceImpl implements StatusService {
    private static StatusServiceImpl instance;
    private StatusDao statusDao;

    private StatusServiceImpl() {
        this.statusDao = StatusDaoImpl.getInstance();
    }

    public static StatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new StatusServiceImpl();
        }

        return instance;
    }

    @Override
    public Status getStatusById(Long statusId) {
        return statusDao.getStatusById(statusId);
    }

    @Override
    public Status getUserStatusByEmail(String email) {
        return statusDao.getUserStatusByEmail(email);
    }

    @Override
    public Status getUserStatusByName(String name) {
        return statusDao.getStatusByName(name);
    }
}
