package xxl.bet.milto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Loads and keeps properties from project.properties file
 *
 * @author alexm2000
 */
public final class ProjectProperties {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectProperties.class);
    private static volatile ProjectProperties instance;

    private static final String DATABASE_URL_PROPERTY_ID = "xxl.bet.milto.jdbc.url";
    private static final String DATABASE_USERNAME_PROPERTY_ID = "xxl.bet.milto.jdbc.user";
    private static final String DATABASE_PASSWORD_PROPERTY_ID = "xxl.bet.milto.jdbc.password";
    private static final String DATABASE_DRIVER_NAME_PROPERTY_ID = "xxl.bet.milto.jdbc.driver-classname";
    private static final String DATABASE_CONNECTION_POOL_PROPERTY_ID = "xxl.bet.milto.jdbc.connection-pool";
    private static final
    String DATABASE_WAIT_AVAILABLE_CONNECTION_TIMEOUT_PROPERTY_ID = "xxl.bet.milto.jdbc.connection.wait-available.timeout";


    private Map<String, String> properties = new HashMap<>();

    public static ProjectProperties getInstance() {
        ProjectProperties localInstance = instance;
        if (localInstance == null) {
            synchronized (ProjectProperties.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ProjectProperties();
                }
            }
        }
        return localInstance;
    }

    public void init() throws IOException {
        Properties properties = new Properties();

        properties.load(ProjectProperties.class.getResourceAsStream("/project.properties"));

        properties.forEach((key, value) -> this.properties.put((String) key, (String) value));
    }

    public Optional<String> getDatabaseUrl() {
        return getStringProperty(DATABASE_URL_PROPERTY_ID);
    }

    public Optional<String> getDatabaseUsername() {
        return getStringProperty(DATABASE_USERNAME_PROPERTY_ID);
    }

    public Optional<String> getDatabasePassword() {
        return getStringProperty(DATABASE_PASSWORD_PROPERTY_ID);
    }

    public Optional<String> getDatabaseDriverName() {
        return getStringProperty(DATABASE_DRIVER_NAME_PROPERTY_ID);
    }

    public Optional<Integer> getDatabaseConnectionPool() {
        return getIntProperty(DATABASE_CONNECTION_POOL_PROPERTY_ID);
    }

    public Optional<Integer> getDatabaseConnectionTimeout() {
        return getIntProperty(DATABASE_WAIT_AVAILABLE_CONNECTION_TIMEOUT_PROPERTY_ID);
    }

    public Optional<String> getStringProperty(final String id) {
        return properties.containsKey(id)
                ? Optional.of(properties.get(id))
                : Optional.empty();
    }

    public Optional<Integer> getIntProperty(final String id) {
        Optional<String> property = getStringProperty(id);

        return property.isPresent() && isInteger(property.get())
                ? Optional.of(Integer.parseInt(property.get()))
                : Optional.empty();
    }

    /** Check if given string is integer */
    private boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
