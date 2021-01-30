package com.epam.xxlbet.milto.connection.mail;

import com.epam.xxlbet.milto.exceptions.ConnectionPoolException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

/**
 * TransportConnectionFactory.
 * Factory of connections to smtp server.
 * Uses provided {@link Session} instance
 * to provide multiple {@link Transport} connections.
 *
 * @author Aliaksei Milto
 */
class TransportConnectionFactory {

    private final Session session;

    TransportConnectionFactory(final Session session) {
        this.session = session;
    }

    /**
     * Get a new {@link Transport} instance.
     * Provided instance is already connected to the smtp server.
     *
     * @return new {@link Transport} instance.
     * @throws ConnectionPoolException
     * if could not get transport from session
     * or of transport could not connect to smtp server.
     */
    Transport getTransport() {
        try {
            Transport transport = session.getTransport();
            transport.connect();
            return transport;
        } catch (MessagingException e) {
            throw new ConnectionPoolException("Something went wrong during connecting to smtp server", e);
        }
    }
}
