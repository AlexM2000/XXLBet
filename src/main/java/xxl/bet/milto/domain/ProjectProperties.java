package xxl.bet.milto.domain;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Loads and keeps properties from project.properties file
 *
 * @author alexm2000
 */
public final class ProjectProperties {
    private static volatile ProjectProperties instance;

    private static final String DATABASE_URL_PROPERTY_ID = "xxl.bet.milto.jdbc.url";
    private static final String DATABASE_USERNAME_PROPERTY_ID = "xxl.bet.milto.jdbc.user";
    private static final String DATABASE_PASSWORD_PROPERTY_ID = "xxl.bet.milto.jdbc.password";
    private static final String DATABASE_DRIVER_NAME_PROPERTY_ID = "xxl.bet.milto.jdbc.driver-classname";

    private Map<String, String> properties;

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
        return getProperty(DATABASE_URL_PROPERTY_ID);
    }

    public Optional<String> getDatabaseUsername() {
        return getProperty(DATABASE_USERNAME_PROPERTY_ID);
    }

    public Optional<String> getDatabasePassword() {
        return getProperty(DATABASE_PASSWORD_PROPERTY_ID);
    }

    public Optional<String> getDatabaseDriverName() {
        return getProperty(DATABASE_DRIVER_NAME_PROPERTY_ID);
    }

    public Optional<String> getProperty(final String id) {
        return properties.containsKey(id)
                ? Optional.of(properties.get(id))
                : Optional.empty();
    }
}
