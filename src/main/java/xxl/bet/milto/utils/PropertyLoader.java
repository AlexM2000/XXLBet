package xxl.bet.milto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static java.util.stream.Collectors.toList;
import static xxl.bet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;

/**
 * Loads and keeps properties from .properties files
 *
 * @author alexm2000
 */
public final class PropertyLoader {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyLoader.class);
    private static volatile PropertyLoader instance;

    private static final String DATABASE_URL_PROPERTY_ID = "xxl.bet.milto.jdbc.url";
    private static final String DATABASE_USERNAME_PROPERTY_ID = "xxl.bet.milto.jdbc.user";
    private static final String DATABASE_PASSWORD_PROPERTY_ID = "xxl.bet.milto.jdbc.password";
    private static final String DATABASE_DRIVER_NAME_PROPERTY_ID = "xxl.bet.milto.jdbc.driver-classname";
    private static final String DATABASE_CONNECTION_POOL_PROPERTY_ID = "xxl.bet.milto.jdbc.connection-pool";
    private static final
    String DATABASE_WAIT_AVAILABLE_CONNECTION_TIMEOUT_PROPERTY_ID = "xxl.bet.milto.jdbc.connection.wait-available.timeout";


    private Map<String, Map<String, String>> properties = new HashMap<>();

    public static PropertyLoader getInstance() {
        PropertyLoader localInstance = instance;
        if (localInstance == null) {
            synchronized (PropertyLoader.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PropertyLoader();
                }
            }
        }
        return localInstance;
    }

    /**
     *  Initializes properties map with all .properties files from classpath.
     */
    public void init() throws IOException {
        Properties properties = new Properties();

        List<String> propertyFiles = Files.walk(Paths.get("/../../resources"))
                .filter(Files::isRegularFile)
                .filter(file -> file.endsWith(".properties"))
                .map(file -> file.toFile().getName())
                .collect(toList());

        for (String propertyFile : propertyFiles) {
            properties.load(PropertyLoader.class.getResourceAsStream(propertyFile));
            Map<String, String> propertiesFromFile = new HashMap<>();
            properties.forEach((key, value) -> propertiesFromFile.put((String) key, (String) value));
            properties.forEach((key, value) -> this.properties.put(propertyFile, propertiesFromFile));
        }

    }

    /**
     *  Initializes .properties file with given path from classpath.
     */
    public void init(final String path) throws IOException {
        Properties properties = new Properties();

        properties.load(PropertyLoader.class.getResourceAsStream("/" + path));

        Map<String, String> propertiesFromFile = new HashMap<>();
        properties.forEach((key, value) -> propertiesFromFile.put((String) key, (String) value));
        properties.forEach((key, value) -> this.properties.put(path, propertiesFromFile));
    }


    public Optional<String> getDatabaseUrl() {
        return getStringProperty(PROJECT_PROPERTIES, DATABASE_URL_PROPERTY_ID);
    }

    public Optional<String> getDatabaseUsername() {
        return getStringProperty(PROJECT_PROPERTIES, DATABASE_USERNAME_PROPERTY_ID);
    }

    public Optional<String> getDatabasePassword() {
        return getStringProperty(PROJECT_PROPERTIES, DATABASE_PASSWORD_PROPERTY_ID);
    }

    public Optional<String> getDatabaseDriverName() {
        return getStringProperty(PROJECT_PROPERTIES, DATABASE_DRIVER_NAME_PROPERTY_ID);
    }

    public Optional<Integer> getDatabaseConnectionPool() {
        return getIntProperty(PROJECT_PROPERTIES, DATABASE_CONNECTION_POOL_PROPERTY_ID);
    }

    public Optional<Integer> getDatabaseConnectionTimeout() {
        return getIntProperty(PROJECT_PROPERTIES, DATABASE_WAIT_AVAILABLE_CONNECTION_TIMEOUT_PROPERTY_ID);
    }

    public Optional<String> getStringProperty(final String filename, final String id) {
        return properties.containsKey(filename) && properties.get(filename).containsKey(id)
                ? Optional.of(properties.get(filename).get(id))
                : Optional.empty();
    }

    public Optional<Integer> getIntProperty(final String filename, final String id) {
        Optional<String> property = getStringProperty(filename, id);

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
