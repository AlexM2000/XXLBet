package com.epam.xxlbet.milto.connection;

import com.epam.xxlbet.milto.exceptions.ConnectionPoolException;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

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
    private static final Lock LOCK = new ReentrantLock();

    private PropertyLoader propertyLoader;
    private List<Connection> allConnections;
    private BlockingQueue<Connection> availableConnections;
    private Semaphore semaphore;
    private int poolSize;
    private int timeout;

    private ConnectionPool() {
        propertyLoader = PropertyLoader.getInstance();

        poolSize = propertyLoader.getDatabaseConnectionPool()
                .orElseGet(() -> {
                    LOG.debug(format("Connection pool size is not specified! Defaulting to %d connections.", DEFAULT_CONNECTION_POOL));
                    return DEFAULT_CONNECTION_POOL;
                });
        LOG.debug("Initialized connection pool size...");

        allConnections = new ArrayList<>(poolSize);
        availableConnections = new ArrayBlockingQueue<>(poolSize);
        LOG.debug("Created connection collections...");

        try {
            LOG.debug("Trying to register database driver...");
            Class.forName(propertyLoader.getDatabaseDriverName().orElseGet(() -> {
                LOG.debug("Can't find mysql database driver! Defaulting to com.mysql.cj.jdbc.Driver");
                return "com.mysql.cj.jdbc.Driver";
            }));
            LOG.debug("Successfully registered database driver...");
        } catch (ClassNotFoundException e) {
            LOG.error("Could not register database driver! Exiting...", e);
            System.exit(1);
        }

        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = new ProxyConnection(DBConnectionUtil.getConnection());
                allConnections.add(connection);
                availableConnections.add(connection);
                LOG.debug("Initialized connection {}...", i + 1);
            } catch (final SQLException | ClassNotFoundException e) {
                LOG.error("Can't create connection to database! Exiting...", e);
                System.exit(1);
            }
        }

        LOG.debug("Initialized connection collections...");

        semaphore = new Semaphore(poolSize, true);

        timeout = propertyLoader.getDatabaseConnectionTimeout()
                .orElseGet(() -> {
                    LOG.debug(format("Available connection timeout is not specified! Defaulting to %d seconds.", DEFAULT_CONNECTION_TIMEOUT));
                    return DEFAULT_CONNECTION_TIMEOUT;
                });
        LOG.debug("Initialized connection timeout...");
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public void closeAllConnections() throws SQLException {
        for (Connection used : allConnections) {
            used.close();
        }
        deregisterDrivers();
    }

    public Connection getConnection() throws InterruptedException {
        try {
            if (semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                LOCK.lock();
                return availableConnections.poll();
            } else {
                throw new ConnectionPoolException("Connection waiting timed out");
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void releaseConnection(final Connection connection) {
        if (!allConnections.contains(connection)) {
            throw new ConnectionPoolException("Unknown connection");
        }
        availableConnections.add(connection);
        semaphore.release();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exp) {
                LOG.error("Error while deregistering drivers...", exp);
            }
        }
    }
}
