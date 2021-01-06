package com.epam.xxlbet.milto.listener;

import com.epam.xxlbet.milto.scheduled.DeleteUnconfirmedUsersJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.xxlbet.milto.utils.connection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.HOURS;

/**
 * WebApplicationListener.
 *
 * @author Aliaksei Milto
 */
@WebListener
public class WebApplicationListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(WebApplicationListener.class);
    private ScheduledExecutorService executorService;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        LOG.debug("Initializing ApplicationContext...");
        ConnectionPool.getInstance();
        LOG.debug("Creating scheduled jobs...");
        executorService = Executors.newSingleThreadScheduledExecutor();
        // Delete users that did not confirm registration every 12 hours
        executorService.scheduleAtFixedRate(DeleteUnconfirmedUsersJob.getInstance(), 0, 12, HOURS);
        LOG.debug("Created DeleteOutdatedVerificationTokensJob");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        LOG.debug("Destroying ApplicationContext...");
        LOG.debug("Closing connections...");

        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            pool.closeAllConnections();
        } catch (SQLException e) {
            LOG.error("Could not close connections!", e);
        }

        LOG.debug("Connections are closed successfully!");
        LOG.debug("ApplicationContext destroyed successfully!");
    }
}
