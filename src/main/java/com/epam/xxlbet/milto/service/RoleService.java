package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Role;

public interface RoleService {
    Role getUserRoleByEmail(String email);
}
