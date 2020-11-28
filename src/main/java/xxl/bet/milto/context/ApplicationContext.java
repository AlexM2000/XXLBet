package xxl.bet.milto.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.utils.DBConnectionUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Contains info about error.
 *
 * @author alexm2000
 */
@WebListener
public class ApplicationContext implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        LOG.debug("Initializing ApplicationContext...");
        Connection connection;

        try {
            connection = DBConnectionUtil.getConnection();
            servletContextEvent.getServletContext().setAttribute("dbconnection", connection);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            LOG.error("Can't complete app initialization! Exiting...", e);
            System.exit(1);
        }

        LOG.debug("ApplicationContext initialized successfully!");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        LOG.debug("Destroying ApplicationContext...");

        Object property = servletContextEvent.getServletContext().getAttribute("dbconnection");

        if (property instanceof Connection) {
            Connection connection = (Connection) property;
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error("Could not close connection!", e);
            }
        } else {
            LOG.error("No attribute of connection is present inside servletContext! Expected Connection, but found {}",
                    property != null ? property.getClass() : null);
        }

        LOG.debug("ApplicationContext destroyed successfully!");
    }
}
