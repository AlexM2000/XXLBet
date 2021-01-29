package com.epam.xxlbet.milto.populator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Transfers data from ResultSet to target.
 * @param <TARGET> Class, to which instance transfer data from ResultSet.
 *
 * @author Aliaksei Milto
 */
public interface ResultSetPopulator<TARGET> {

    /**
     * Transfer data from ResultSet to instance of TARGET class.
     *
     * @param source Result set.
     * @return TARGET instance with data from result set.
     */
    TARGET populate(ResultSet source) throws SQLException;
}
