package com.epam.xxlbet.milto.listener;

import com.epam.xxlbet.milto.connection.ConnectionPool;
import com.epam.xxlbet.milto.scheduled.DeleteExpiredPasswordRequestsJob;
import com.epam.xxlbet.milto.scheduled.DeleteFinishedMatchesJob;
import com.epam.xxlbet.milto.scheduled.DeleteFinishedTournamentsJob;
import com.epam.xxlbet.milto.scheduled.DeleteUnconfirmedUsersJob;
import com.epam.xxlbet.milto.scheduled.GenerateMatchResultJob;
import com.epam.xxlbet.milto.scheduled.RefreshPropertyFilesJob;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.service.impl.mail.JavaxEmailSenderImpl;
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
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
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
    private EmailSender emailSender;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        LOG.debug("Initializing ApplicationContext...");

        LOG.debug("Creating scheduled jobs...");
        executorService = Executors.newSingleThreadScheduledExecutor();

        // Every 12 hours delete users that did not confirm registration
        executorService.scheduleAtFixedRate(DeleteUnconfirmedUsersJob.getInstance(), 1, 12, HOURS);
        // Every 2 days delete finished tournaments
        executorService.scheduleAtFixedRate(DeleteFinishedTournamentsJob.getInstance(), 1, 2, DAYS);
        // Every 8 hours delete finished matches
        executorService.scheduleAtFixedRate(DeleteFinishedMatchesJob.getInstance(), 1, 8, HOURS);
        // Every 30 seconds refresh content of .properties files to server
        executorService.scheduleAtFixedRate(RefreshPropertyFilesJob.getInstance(), 1, 30, SECONDS);
        // Generate random matches and update balance of users that bet on complete match every minute
        executorService.scheduleAtFixedRate(GenerateMatchResultJob.getInstance(), 0, 1, MINUTES);
        // Delete expired password change requests every 24 hours
        executorService.scheduleAtFixedRate(DeleteExpiredPasswordRequestsJob.getInstance(), 1, 24, HOURS);

        LOG.debug("Created scheduled jobs...");
        LOG.debug("Initialized ApplicationContext...");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        LOG.debug("Destroying ApplicationContext...");
        LOG.debug("Closing connections...");

        try {
            ConnectionPool.getInstance().closeAllConnections();
        } catch (SQLException e) {
            LOG.error("Could not close connections!", e);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        emailSender = JavaxEmailSenderImpl.getInstance();
        emailSender.shutdown();

        LOG.debug("Connections are closed successfully!");
        LOG.debug("ApplicationContext destroyed successfully!");
    }
}
