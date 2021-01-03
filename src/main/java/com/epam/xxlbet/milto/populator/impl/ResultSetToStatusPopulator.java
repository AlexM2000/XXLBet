package com.epam.xxlbet.milto.populator.impl;

import com.epam.xxlbet.milto.domain.Status;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.xxlbet.milto.domain.Status.ACTIVE;
import static com.epam.xxlbet.milto.domain.Status.BANNED;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.ACTIVE_STATUS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.BANNED_STATUS;

public class ResultSetToStatusPopulator implements ResultSetPopulator<ResultSet, Status> {
    private static ResultSetToStatusPopulator instance;

    private ResultSetToStatusPopulator() {
    }

    public static ResultSetToStatusPopulator getInstance() {
        if (instance == null) {
            instance = new ResultSetToStatusPopulator();
        }

        return instance;
    }

    @Override
    public Status populate(ResultSet source) throws SQLException {
        Status status = null;
        String name = source.getString(2);

        switch (name) {
            case ACTIVE_STATUS:
                status = ACTIVE;
                break;
            case BANNED_STATUS:
                status = BANNED;
                break;
        }

        return status;
    }
}
