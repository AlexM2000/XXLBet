package xxl.bet.milto.utils.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.utils.PropertyLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;
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

        availableConnections = new ArrayBlockingQueue<>(poolSize);
        usedConnections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DBConnectionUtil.getConnection();
                availableConnections.add(connection);
            } catch (IOException | SQLException | ClassNotFoundException e) {
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
        for (Connection used : usedConnections) {
            used.commit();
            used.close();
            usedConnections.poll();
        }

        for (Connection available : availableConnections) {
            available.close();
            availableConnections.poll();
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
        } catch (InterruptedException e) {
            throw e;
        } finally {
            LOCK.unlock();
        }
    }

    public void releaseConnection(final Connection connection) {
        if (!availableConnections.contains(connection)) {
            throw new RuntimeException("Unknown connection");
        }
        availableConnections.add(connection);
        semaphore.release();
    }
}
