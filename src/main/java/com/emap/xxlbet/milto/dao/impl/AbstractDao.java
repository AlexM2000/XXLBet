package com.emap.xxlbet.milto.dao.impl;

import com.emap.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.emap.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.emap.xxlbet.milto.utils.connection.ConnectionPool;

import java.io.IOException;

abstract class AbstractDao {
    private static final String ERROR_MSG_BEGIN = "Something wrong happened while executing";
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
    private PropertyLoader loader;
    private ConnectionPool connectionPool;
    private String propertiesFileWithQueriesName;

    protected AbstractDao(final String propertiesFileName) {
        try {
            loader = PropertyLoader.getInstance();
            loader.init(propertiesFileName);
            propertiesFileWithQueriesName = propertiesFileName;
        } catch (final IOException e) {
            LOG.error("Could not load queries for database! Exiting...");
            System.exit(1);
        }

        connectionPool = ConnectionPool.getInstance();
    }

    protected static Logger getLogger() {
        return LOG;
    }

    protected PropertyLoader getPropertyLoader() {
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

    protected String getPropertiesFileWithQueriesName() {
        return propertiesFileWithQueriesName;
    }
}
