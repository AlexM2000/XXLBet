package com.epam.xxlbet.milto.connection.jdbc;

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
 * Pool of jdbc connections to database.
 *
 * @author Aliaksei Milto
 */
public final class JdbcConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcConnectionPool.class);
    private static final int DEFAULT_CONNECTION_POOL = 5;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 5;
    private static JdbcConnectionPool instance;

    private PropertyLoader propertyLoader;
    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> busyConnections;

    private int poolSize;
    private int timeout;

    private JdbcConnectionPool() {
        init();
    }

    public static JdbcConnectionPool getInstance() {
        if (instance == null) {
            instance = new JdbcConnectionPool();
        }
        return instance;
    }

    /**
     * Close all free and busy jdbc connections.
     * Part of shutdown application flow.
     *
     * @throws SQLException if something went wrong during closing connections.
     */
    public void closeAllConnections() throws SQLException {
        for (Connection connection : busyConnections) {
            LOG.debug("Closing busy jdbc connection...");
            connection.commit();
            connection.close();
        }

        for (Connection connection : freeConnections) {
            LOG.debug("Closing free jdbc connection...");
            if (connection instanceof JdbcConnectionProxy) {
                ((JdbcConnectionProxy) connection).terminate();
            } else {
                connection.close();
            }
        }

        deregisterDrivers();
        busyConnections.clear();
        freeConnections.clear();
    }

    /**
     * Try to get jdbc connection from connection pool,
     * waiting up for timeout, specified in project properties.
     * Connection is established and ready for use.
     *
     * @return connection
     * @throws ConnectionPoolException
     * if timeout elapsed before free connection is available
     * or there are too many busy connections.
     * @throws InterruptedException if interrupted while waiting for connection.
     */
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

    /**
     * Release given jdbc connection and make it available for other clients.
     *
     * @param connection connection that is needed to release
     * @throws ConnectionPoolException if connection type is unknown
     * or if connection is not busy.
     * @throws InterruptedException if interrupted while releasing connection
     */
    public void releaseConnection(final Connection connection) throws InterruptedException {
        if (!(connection instanceof JdbcConnectionProxy)) {
            throw new ConnectionPoolException("Unknown connection");
        }

        if (!busyConnections.remove(connection)) {
            throw new ConnectionPoolException("Connection is not busy");
        }

        freeConnections.put(connection);
    }

    /**
     * Initialize jdbc connection pool.
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
     * Get jdbc connection pool from project properties.
     * If project properties doesn't have defined connection pool
     * returns default value {@link #DEFAULT_CONNECTION_POOL}.
     */
    private int getDatabaseConnectionPool() {
        return propertyLoader.getDatabaseConnectionPool()
                .orElseGet(() -> {
                    LOG.debug(format("Connection pool size is not specified! Defaulting to %d connections.", DEFAULT_CONNECTION_POOL));
                    return DEFAULT_CONNECTION_POOL;
                });
    }

    /**
     * Get name of jdbc driver from project.properties file
     * and register this driver.
     * If can't find name of database driver,
     * tries to register default "com.mysql.cj.jdbc.Driver".
     *
     * @throws ConnectionPoolException if can't find database driver class.
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
     * Create jdbc connection instances and put them to freeConnections.
     *
     * @throws ConnectionPoolException if something went wrong when creating connection.
     */
    private void createConnections() {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = new JdbcConnectionProxy(DBConnectionUtil.getConnection());
                freeConnections.add(connection);
                LOG.debug("Initialized connection {} of {}...", i + 1, poolSize);
            } catch (final SQLException e) {
                throw new ConnectionPoolException("Can't create connection to database!", e);
            }
        }
    }

    /**
     * Get jdbc connection timeout from project properties.
     * If project properties doesn't have defined connection pool
     * returns default value {@link #DEFAULT_CONNECTION_TIMEOUT}.
     */
    private int getConnectionTimeout() {
        return propertyLoader.getDatabaseConnectionTimeout()
                .orElseGet(() -> {
                    LOG.debug(format("Available connection timeout is not specified! Defaulting to %d seconds.", DEFAULT_CONNECTION_TIMEOUT));
                    return DEFAULT_CONNECTION_TIMEOUT;
                });
    }

    /**
     * Deregister all jdbc drivers.
     * Part of the shutdown application flow.
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
