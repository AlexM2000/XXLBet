package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Role;

/**
 * RoleDao.
 *
 * @author Aliaksei Milto
 */
public interface RoleDao {

    /**
     * Get {@link Role} of given user.
     *
     * @param email user email
     * @return user role
     */
    Role getUserRoleByEmail(String email);

    /**
     * Find {@link Role} by id
     *
     * @param roleId role id
     * @return {@link Role}
     */
    Role getRoleById(Long roleId);

    /**
     * Find {@link Role} by name
     *
     * @param name role name
     * @return {@link Role}
     */
    Role getRoleByName(String name);
}
