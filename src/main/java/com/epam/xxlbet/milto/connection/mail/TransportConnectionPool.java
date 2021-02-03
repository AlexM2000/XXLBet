package com.epam.xxlbet.milto.connection.mail;

import com.epam.xxlbet.milto.exceptions.ConnectionPoolException;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MAIL_PROPERTIES;
import static java.lang.String.format;

/**
 * TransportConnectionPool.
 * Pool of connections to mail server.
 * In this documentation {@link Transport} and "smtp connection"
 * are synonymous.
 *
 * @author Aliaksei Milto
 */
public class TransportConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(TransportConnectionPool.class);
    private static final int DEFAULT_CONNECTION_POOL = 5;
    private static TransportConnectionPool instance;

    private final TransportConnectionFactory factory;
    private final PropertyLoader loader;
    private final Session session;
    private final int poolSize;

    private BlockingQueue<Transport> freeConnections;
    private Queue<Transport> busyConnections;

    /**
     * Create singleton session instance.
     * Get number of smtp connections from mail.properties.
     * Create {@link TransportConnectionFactory}
     * which will create smtp connections from previously created session.
     */
    private TransportConnectionPool() {
        this.loader = PropertyLoader.getInstance();

        Properties props = new Properties();

        props.put("mail.smtp.host", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.host").get()); //SMTP Host
        props.put("mail.smtp.port", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.port").get()); //TLS Port
        props.put("mail.smtp.auth", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.auth").get()); //enable authentication
        props.put("mail.smtp.starttls.enable", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.starttls.enable").get()); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.ssl.trust").get());

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        loader.getStringProperty(MAIL_PROPERTIES, "username").get(), loader.getStringProperty(MAIL_PROPERTIES, "password").get()
                );
            }
        };

        this.session = Session.getInstance(props, authenticator);
        this.poolSize = getTransportConnectionPool();
        this.factory = new TransportConnectionFactory(session);
        createConnections();
    }

    public static TransportConnectionPool getInstance() {
        if (instance == null) {
            instance = new TransportConnectionPool();
        }

        return instance;
    }

    /**
     * Close all smtp connections.
     * Part of shutdown application flow.
     */
    public void closeAllConnections() throws MessagingException {
        for (Transport transport : busyConnections) {
            LOG.debug("Closing busy smtp connection...");
            transport.close();
        }

        for (Transport transport : freeConnections) {
            LOG.debug("Closing free smtp connection...");
            transport.close();
        }

        busyConnections.clear();
        freeConnections.clear();
    }

    /**
     * Get smtp connection from connection pool.
     * This method neither checks, if obtained transport instance
     * is actually connected to smtp server, nor performs reconnection,
     * so the caller will need to do this by itself.
     *
     * @return smtp connection
     * @throws ConnectionPoolException
     * if could not obtain free connection from pool
     * or if could not change it to busy state.
     */
    public Transport getConnection() {
        Transport transport;

        try {
            transport = freeConnections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Something went wrong during getting smtp connection", e);
        }

        if (!busyConnections.offer(transport)) {
            throw new ConnectionPoolException("Could not add smtp connection to busy connections");
        }

        return transport;
    }

    /**
     * Release given {@link Transport} instance
     * and make it available for obtaining by other callers.
     *
     * @throws ConnectionPoolException
     * if smtp connection is already released
     * or if didn't succeed to make it available
     */
    public void releaseConnection(Transport transport) {
        if (!busyConnections.remove(transport)) {
            throw new ConnectionPoolException("Smtp connection is not busy");
        }

        try {
            freeConnections.put(transport);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Something went wrong during releasing smtp connection", e);
        }
    }

    public Session getSession() {
        return session;
    }

    /**
     * Create smtp connections and put them to {@link #freeConnections}
     */
    private void createConnections() {
        freeConnections = new ArrayBlockingQueue<>(poolSize);
        busyConnections = new ArrayDeque<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Transport transport = factory.getTransport();
            freeConnections.add(transport);
            LOG.debug("Created smtp connection {} of {}", i + 1, poolSize);
        }
    }

    /**
     * Get mail server connection pool from project properties.
     * If project properties doesn't have defined connection pool
     * returns default value {@link #DEFAULT_CONNECTION_POOL}.
     */
    private int getTransportConnectionPool() {
        return loader.getIntProperty(MAIL_PROPERTIES, "xxl.bet.milto.transport.connection-pool")
                .orElseGet(() -> {
                    LOG.debug(format("Connection pool size is not specified! Defaulting to %d connections.", DEFAULT_CONNECTION_POOL));
                    return DEFAULT_CONNECTION_POOL;
                });
    }
}
