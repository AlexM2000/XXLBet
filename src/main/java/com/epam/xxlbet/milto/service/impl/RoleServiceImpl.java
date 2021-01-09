package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.RoleDao;
import com.epam.xxlbet.milto.dao.impl.RoleDaoImpl;
import com.epam.xxlbet.milto.domain.Role;
import com.epam.xxlbet.milto.service.RoleService;

public class RoleServiceImpl implements RoleService {
    private static RoleServiceImpl instance;
    private RoleDao roleDao;

    private RoleServiceImpl() {
        roleDao = RoleDaoImpl.getInstance();
    }

    public static RoleServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }

        return instance;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleDao.getUserRoleById(roleId);
    }

    @Override
    public Role getUserRoleByEmail(String email) {
        return roleDao.getUserRoleByEmail(email);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
