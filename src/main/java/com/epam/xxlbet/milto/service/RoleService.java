package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Role;

public interface RoleService {
    Role getRoleById(Long roleId);
    Role getUserRoleByEmail(String email);
    Role getRoleByName(String name);
}
