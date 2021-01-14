package com.epam.xxlbet.milto.populator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transfers data from ResultSet to target.
 *
 * @author Aliaksei Milto
 */
public interface ResultSetPopulator<TARGET> {
    TARGET populate(ResultSet source) throws SQLException;
}
