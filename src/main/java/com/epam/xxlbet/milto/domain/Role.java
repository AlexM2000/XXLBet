package com.epam.xxlbet.milto.domain;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.ADMIN_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BOOKMAKER_ROLE;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.CLIENT_ROLE;

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
