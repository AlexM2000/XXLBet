package com.epam.xxlbet.milto.populator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transfers data from ResultSet to target.
 *
 * @author Aliaksei Milto
 */
public interface ResultSetPopulator<SOURCE extends ResultSet, TARGET> {
    TARGET populate(SOURCE source) throws SQLException;
}
