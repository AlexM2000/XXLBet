package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Role;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.xxlbet.milto.domain.Role.ADMIN;
import static com.epam.xxlbet.milto.domain.Role.BOOKMAKER;
import static com.epam.xxlbet.milto.domain.Role.CLIENT;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.ADMIN_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BOOKMAKER_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.CLIENT_ROLE;

/**
 * ResultSetToRolePopulator.
 *
 * @author Aliaksei Milto
 */
public class ResultSetToRolePopulator implements ResultSetPopulator<Role> {
    private static ResultSetToRolePopulator instance;

    private ResultSetToRolePopulator() {
    }

    public static ResultSetToRolePopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToRolePopulator();
        }

        return instance;
    }

    @Override
    public Role populate(ResultSet source) throws SQLException {
        Role role;
        String name = source.getString(2);

        switch (name) {
            default:
            case CLIENT_ROLE:
                role = CLIENT;
                break;
            case ADMIN_ROLE:
                role = ADMIN;
                break;
            case BOOKMAKER_ROLE:
                role = BOOKMAKER;
                break;
        }

        return role;
    }
}
