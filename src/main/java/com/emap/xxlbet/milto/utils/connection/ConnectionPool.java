package com.emap.xxlbet.milto.utils.connection;

import com.emap.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

public final class ConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static final int DEFAULT_CONNECTION_POOL = 5;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 5;
    private static volatile ConnectionPool instance;
    private static final Lock LOCK = new ReentrantLock();

    private PropertyLoader properties;
    private List<Connection> allConnections;
    private BlockingQueue<Connection> availableConnections;
    private Semaphore semaphore;
    private int poolSize;
    private int timeout;

    private ConnectionPool() {
        properties = PropertyLoader.getInstance();

        poolSize = properties.getDatabaseConnectionPool()
                .orElseGet(() -> {
                    LOG.debug(format("Connection pool size is not specified! Defaulting to %d connections.", DEFAULT_CONNECTION_POOL));
                    return DEFAULT_CONNECTION_POOL;
                });
        LOG.debug("Initialized connection pool size...");

        allConnections = new ArrayList<>(poolSize);
        availableConnections = new ArrayBlockingQueue<>(poolSize);
        LOG.debug("Initialized collections...");

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

        semaphore = new Semaphore(poolSize, true);

        timeout = properties.getDatabaseConnectionTimeout()
                .orElseGet(() -> {
                    LOG.debug(format("Available connection timeout is not specified! Defaulting to %d seconds.", DEFAULT_CONNECTION_TIMEOUT));
                    return DEFAULT_CONNECTION_TIMEOUT;
                });
        LOG.debug("Initialized connection timeout...");
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    public void closeAllConnections() throws SQLException {
        for (Connection used : allConnections) {
            used.close();
        }
    }

    public Connection getConnection() throws InterruptedException {
        try {
            if (semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                LOCK.lock();
                return availableConnections.poll();
            } else {
                throw new RuntimeException("Connection waiting timed out");
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void releaseConnection(final Connection connection) {
        if (!allConnections.contains(connection)) {
            throw new RuntimeException("Unknown connection");
        }
        availableConnections.add(connection);
        semaphore.release();
    }
}
