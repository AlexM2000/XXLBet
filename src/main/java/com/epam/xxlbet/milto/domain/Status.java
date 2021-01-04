package com.epam.xxlbet.milto.domain;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.ACTIVE_STATUS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BANNED_STATUS;

public enum Status {
    ACTIVE(0, ACTIVE_STATUS), BANNED(1, BANNED_STATUS);

    private int id;
    private String name;

    Status(final int id, final String name) {
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
