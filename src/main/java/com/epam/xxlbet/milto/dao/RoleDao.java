package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Role;

public interface RoleDao {
    Role getUserRoleByEmail(String email);
}
