package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.RoleDao;
import com.epam.xxlbet.milto.domain.Role;
import com.epam.xxlbet.milto.populator.impl.ResultSetToRolePopulator;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_ROLES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_ROLE_BY_EMAIL;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    private static RoleDaoImpl instance;

    private RoleDaoImpl() {
        super(FILE_WITH_QUERIES_FOR_TABLE_ROLES, ResultSetToRolePopulator.getInstance());
    }

    public static RoleDaoImpl getInstance() {
        if (instance == null) {
            instance = new RoleDaoImpl();
        }

        return instance;
    }

    @Override
    public Role getUserRoleByEmail(String email) {
        return executeForSingleResult(SELECT_USER_ROLE_BY_EMAIL, email);
    }
}