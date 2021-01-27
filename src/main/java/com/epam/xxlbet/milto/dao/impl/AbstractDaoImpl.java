package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.exceptions.DaoException;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.populator.ResultSetPopulator;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.connection.ConnectionPool;
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
 * AbstractDao.
 *
 * @author Aliaksei Milto
 */
abstract class AbstractDaoImpl<T> {
    private static final String ERROR_MSG_BEGIN = "Something wrong happened while executing";
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDaoImpl.class);
    private PropertyLoader loader;
    private ConnectionPool connectionPool;
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

        connectionPool = ConnectionPool.getInstance();
    }

    protected List<T> executeQuery(final String queryId, Object... params) {
        List<T> entities = new ArrayList<>();

        try(final Connection connection = getConnectionPool().getConnection();) {

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

    protected T executeForSingleResult(String queryId, Object... params) {
        List<T> items = executeQuery(queryId, params);
        return items.size() == 1 ? items.get(0) : null;
    }

    protected void executeUpdate(String queryId, Object... params) {
        try (final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(queryId));
            setParameters(statement, params);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new DaoException(getErrorMsgBegin() + " executeUpdate...", e);
        }
    }

    protected void execute(String queryId, Object... params) {
        try (final Connection connection = getConnectionPool().getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(getSqlById(queryId));
            setParameters(statement, params);
            statement.execute();
        } catch (SQLException | InterruptedException e) {
            throw new DaoException(getErrorMsgBegin() + " execute...", e);
        }
    }

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

    protected ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    protected String getErrorMsgBegin() {
        return ERROR_MSG_BEGIN;
    }

    protected String getSqlById(final String id) {
        return getPropertyLoader().getStringProperty(getPropertiesFileWithQueriesName(), id)
                .orElseThrow(() -> new PropertyNotFoundException("Could not find query in properties!"));
    }

    private String getPropertiesFileWithQueriesName() {
        return propertiesFileWithQueriesName;
    }
}
