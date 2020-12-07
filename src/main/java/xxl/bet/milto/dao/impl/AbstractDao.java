package xxl.bet.milto.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.exceptions.PropertyNotFoundException;
import xxl.bet.milto.utils.PropertyLoader;
import xxl.bet.milto.utils.connection.ConnectionPool;

import java.io.IOException;

public abstract class AbstractDao {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
    private PropertyLoader loader;
    private ConnectionPool connectionPool;

    protected AbstractDao(final String propertiesFileName) {
        try {
            PropertyLoader.getInstance().init(propertiesFileName);
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

    protected String getSql(final String filename, String id) {
        return getPropertyLoader().getStringProperty(filename, id)
                .orElseThrow(() -> new PropertyNotFoundException("Could not find query in properties!"));
    }
}
