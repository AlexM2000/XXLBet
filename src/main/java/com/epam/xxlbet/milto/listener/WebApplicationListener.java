package com.epam.xxlbet.milto.listener;

import com.epam.xxlbet.milto.connection.ConnectionPool;
import com.epam.xxlbet.milto.scheduled.DeleteFinishedMatchesJob;
import com.epam.xxlbet.milto.scheduled.DeleteFinishedTournamentsJob;
import com.epam.xxlbet.milto.scheduled.DeleteUnconfirmedUsersJob;
import com.epam.xxlbet.milto.scheduled.RefreshPropertyFilesJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

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

        // Every 12 hours delete users that did not confirm registration
        executorService.scheduleAtFixedRate(DeleteUnconfirmedUsersJob.getInstance(), 0, 12, HOURS);
        // Every 2 days delete finished tournaments
        executorService.scheduleAtFixedRate(DeleteFinishedTournamentsJob.getInstance(), 0, 2, DAYS);
        // Every 8 hours delete finished matches
        executorService.scheduleAtFixedRate(DeleteFinishedMatchesJob.getInstance(), 0, 8, HOURS);
        // Every 30 seconds refresh content of .properties files to server
        executorService.scheduleAtFixedRate(RefreshPropertyFilesJob.getInstance(), 1, 30, SECONDS);

        LOG.debug("Created scheduled jobs...");
        LOG.debug("Initialized ApplicationContext...");
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
