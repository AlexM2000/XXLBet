package com.epam.xxlbet.milto.connection;

import com.epam.xxlbet.milto.exceptions.ConnectionPoolException;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * ConnectionPool.
 *
 * @author Aliaksei Milto
 */
public final class ConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static final int DEFAULT_CONNECTION_POOL = 5;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 5;
    private static ConnectionPool instance;

    private PropertyLoader propertyLoader;
    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> busyConnections;
    private int poolSize;
    private int timeout;

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public void closeAllConnections() throws SQLException {
        for (Connection connection : busyConnections) {
            connection.commit();
            connection.close();
        }

        for (Connection connection : freeConnections) {
            if (connection instanceof ProxyConnection) {
                ((ProxyConnection) connection).terminate();
            } else {
                connection.close();
            }
        }

        deregisterDrivers();
    }

    public Connection getConnection() throws InterruptedException {
        Connection connection = freeConnections.poll(timeout, MILLISECONDS);
        if (null == connection) {
            throw new ConnectionPoolException("Waiting for free connection too long");
        }

        if (!busyConnections.offer(connection)) {
            throw new ConnectionPoolException("Could not add connection to busy connections");
        }

        return connection;
    }

    public void releaseConnection(final Connection connection) throws InterruptedException {
        if (!(connection instanceof ProxyConnection)) {
            throw new ConnectionPoolException("Unknown connection " + connection.getClass());
        }

        if (!busyConnections.remove(connection)) {
            throw new ConnectionPoolException("busyConnections is already empty " + freeConnections.toString());
        }

        freeConnections.put(connection);
    }

    /**
     * Initialize connection pool.
     */
    private void init() {
        propertyLoader = PropertyLoader.getInstance();

        poolSize = getDatabaseConnectionPool();
        LOG.debug("Initialized connection pool size...");

        freeConnections = new ArrayBlockingQueue<>(poolSize);
        busyConnections = new ArrayDeque<>(poolSize);
        LOG.debug("Created connection collections...");

        registerDriver();
        LOG.debug("Registered driver...");

        createConnections();
        LOG.debug("Initialized connection collection...");

        timeout = getConnectionTimeout();
        LOG.debug("Initialized connection timeout...");
    }

    /**
     * Get database connection pool from project properties.
     * If project properties doesn't have defined connection pool
     * returns default value.
     */
    private int getDatabaseConnectionPool() {
        return propertyLoader.getDatabaseConnectionPool()
                .orElseGet(() -> {
                    LOG.debug(format("Connection pool size is not specified! Defaulting to %d connections.", DEFAULT_CONNECTION_POOL));
                    return DEFAULT_CONNECTION_POOL;
                });
    }

    /**
     * Register driver.
     * Shut down whole application immediately, if can't find driver.
     */
    private void registerDriver() {
        try {
            LOG.debug("Trying to register database driver...");

            Class.forName(
                    propertyLoader.getDatabaseDriverName()
                            .orElseGet(() -> {
                                LOG.debug("Can't find mysql database driver! Defaulting to com.mysql.cj.jdbc.Driver");
                                return "com.mysql.cj.jdbc.Driver";
                            })
            );

        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Could not register database driver!", e);
        }
    }

    /**
     * Create ProxyConnection instances and put them to freeConnections.
     * If exception occurred while creating or adding connection
     * shut down application immediately.
     */
    private void createConnections() {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = new ProxyConnection(DBConnectionUtil.getConnection());
                freeConnections.add(connection);
                LOG.debug("Initialized connection {}...", i + 1);
            } catch (final SQLException | IllegalStateException e) {
                throw new ConnectionPoolException("Can't create connection to database!", e);
            }
        }
    }

    /**
     * Get database connection timeout from project properties.
     * If project properties doesn't have defined connection pool
     * returns default value.
     */
    private int getConnectionTimeout() {
        return propertyLoader.getDatabaseConnectionTimeout()
                .orElseGet(() -> {
                    LOG.debug(format("Available connection timeout is not specified! Defaulting to %d seconds.", DEFAULT_CONNECTION_TIMEOUT));
                    return DEFAULT_CONNECTION_TIMEOUT;
                });
    }

    /**
     * Deregister all drivers.
     * Part of the shutdown application flow
     */
    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOG.error("Error while deregistering drivers...", e);
            }
        }
    }
}
