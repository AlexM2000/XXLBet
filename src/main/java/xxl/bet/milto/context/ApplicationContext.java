package xxl.bet.milto.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.utils.connection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * ApplicationContext.
 *
 * @author alexm2000
 */
@WebListener
public class ApplicationContext implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        LOG.debug("Initializing ApplicationContext...");
        ConnectionPool.getInstance();
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
