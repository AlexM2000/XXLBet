package com.epam.xxlbet.milto.populator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetPopulator<SOURCE extends ResultSet, TARGET> {
    TARGET populate(SOURCE source) throws SQLException;
}
