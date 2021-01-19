package com.epam.xxlbet.milto.domain;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.ADMIN_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BOOKMAKER_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.CLIENT_ROLE;

/**
 * User role.
 * CLIENT - common role. Can create bets, pay with credit card.
 * ADMIN - CLIENT + can create sports, tournaments, opponents, matches,
 * sets coefficients level for opponents.
 * BOOKMAKER - ADMIN + can change user roles.
 * Check {@link com.epam.xxlbet.milto.filters.RoleFilter}
 * and {@link com.epam.xxlbet.milto.filters.authenticator.AuthenticatorImpl}
 *
 * @author Aliaksei Milto
 */
public enum Role {
    CLIENT(0, CLIENT_ROLE), ADMIN(1, ADMIN_ROLE), BOOKMAKER(2, BOOKMAKER_ROLE);

    private int id;
    private String name;

    Role(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
