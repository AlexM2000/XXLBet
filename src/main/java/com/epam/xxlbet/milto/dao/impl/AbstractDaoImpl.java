package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.exceptions.DaoException;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.connection.jdbc.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDaoImpl.
 *
 * @author Aliaksei Milto
 */
abstract class AbstractDaoImpl<T> {
    private static final String ERROR_MSG_BEGIN = "Something wrong happened while executing";
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDaoImpl.class);
    private PropertyLoader loader;
    private JdbcConnectionPool connectionPool;
    private String propertiesFileWithQueriesName;
    private ResultSetPopulator<T> populator;

    protected AbstractDaoImpl(final String propertiesFileName, final ResultSetPopulator<T> populator) {
        try {
            loader = PropertyLoader.getInstance();
            loader.init(propertiesFileName);
            propertiesFileWithQueriesName = propertiesFileName;
            this.populator = populator;
        } catch (final IOException e) {
            throw new DaoException("Could not load queries for database!", e);
        }

        connectionPool = JdbcConnectionPool.getInstance();
    }

    /**
     * Executes query with given .properties query id and parameters
     * and returns list of data acquired from result set.
     *
     * @param queryId query id
     * @param params query parameters
     * @return List of data from database
     * @throws DaoException if something went wrong when getting connection or executing statement
     */
    protected List<T> executeQuery(final String queryId, Object... params) {
        List<T> entities = new ArrayList<>();

        try (Connection connection = getConnectionPool().getConnection();) {

            final PreparedStatement statement = connection.prepareStatement(getSqlById(queryId));

            setParameters(statement, params);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T entity = populator.populate(resultSet);
                entities.add(entity);
            }

            statement.close();
        } catch (InterruptedException | SQLException e) {
            throw new DaoException(getErrorMsgBegin() + " executeQuery...", e);
        }

        return entities;
    }

    /**
     * Execute query with given .properties query id and parameters
     * and expectation to acquire only one result in result set.
     * If result is not equal to one, return null.
     *
     * @param queryId query id
     * @param params query parameters
     */
    protected T executeForSingleResult(String queryId, Object... params) {
        List<T> items = executeQuery(queryId, params);
        return items.size() == 1 ? items.get(0) : null;
    }

    /**
     * Executes SQL statement with given .properties statement id and parameters.
     * It may be an INSERT, UPDATE, or DELETE statement
     * or an SQL statement that returns nothing, such as an SQL DDL statement.
     *
     * @param statementId statement id
     * @param params statement parameters
     */
    protected void executeUpdate(String statementId, Object... params) {
        try (Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(statementId));
            setParameters(statement, params);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DaoException(getErrorMsgBegin() + " executeUpdate...", e);
        }
    }

    /**
     * Set parameters to sql statement.
     *
     * @param statement {@link PreparedStatement}
     * @param parameters statement parameters
     */
    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            int parameterIndex = i + 1;
            if (parameters[i] != null) {
                statement.setObject(parameterIndex, parameters[i]);
            } else {
                statement.setNull(parameterIndex, Types.NULL);
            }
        }
    }

    protected static Logger getLogger() {
        return LOG;
    }

    private PropertyLoader getPropertyLoader() {
        return loader;
    }

    protected JdbcConnectionPool getConnectionPool() {
        return connectionPool;
    }

    protected String getErrorMsgBegin() {
        return ERROR_MSG_BEGIN;
    }

    /**
     * Get SQL statement with given id
     * from .properties file with name {@link #propertiesFileWithQueriesName}
     *
     * @param id statement id
     * @return statement
     * @throws PropertyNotFoundException if cannot with query with given id
     * in .properties file with name {@link #propertiesFileWithQueriesName}
     */
    protected String getSqlById(final String id) {
        return getPropertyLoader().getStringProperty(getPropertiesFileWithQueriesName(), id)
                .orElseThrow(() -> new PropertyNotFoundException("Could not find query in properties!"));
    }

    private String getPropertiesFileWithQueriesName() {
        return propertiesFileWithQueriesName;
    }
}
