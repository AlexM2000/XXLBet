package xxl.bet.milto.utils.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.exceptions.PropertyNotFoundException;
import xxl.bet.milto.utils.ProjectProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnectionUtil.
 *
 * @author alexm2000
 */
public final class DBConnectionUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DBConnectionUtil.class);

    /**
     * Private constructor.
     */
    private DBConnectionUtil() { }

    /**
     * Establishes connection with database.
     *
     * @return Established connection.
     * @throws SQLException if cannot establish connection with database.
     * @throws IOException if can't find file that contains connection properties.
     * @throws PropertyNotFoundException if can't find property inside properties file that needs database.
     * @throws ClassNotFoundException if can't find driver for database.
     */
    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection connection;
        ProjectProperties properties = ProjectProperties.getInstance();

        try {
            properties.init();
            String driverName = properties.getDatabaseDriverName()
                    .orElseThrow(() -> new PropertyNotFoundException("Can't find database driver name"));
            String url = properties.getDatabaseUrl()
                    .orElseThrow(() -> new PropertyNotFoundException("Can't find database url"));
            String userName = properties.getDatabaseUsername()
                    .orElseThrow(() -> new PropertyNotFoundException("Can't find database username"));
            String password = properties.getDatabasePassword()
                    .orElseThrow(() -> new PropertyNotFoundException("Can't find database password"));

            Class.forName(driverName);
            LOG.debug("Successfully registered driver!");

            connection = DriverManager.getConnection(url, userName, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw e;
        }

        if (connection != null) {
            LOG.debug("Connection was successful!");
        }

        return connection;
    }
}
