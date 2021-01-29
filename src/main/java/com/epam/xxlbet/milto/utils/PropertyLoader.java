package com.epam.xxlbet.milto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MAIL_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_BE_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_EN_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_RU_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.unmodifiableMap;

/**
 * Loads and keeps properties from .properties files
 *
 * @author Aliaksei Milto
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

    private PropertyLoader() {
        try {
            init();
        } catch (final IOException | URISyntaxException e) {
            LOG.error("Could not initialize .properties files in PropertyLoader() constructor", e);
        }
    }

    public static PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }

        return instance;
    }

    /**
     *  Initializes properties map with all .properties files from classpath.
     */
    private void init() throws IOException, URISyntaxException {
        LOG.debug("Initializing project.properties...");

        init(PROJECT_PROPERTIES);

        //locales
        init(MESSAGES_EN_PROPERTIES);
        init(MESSAGES_RU_PROPERTIES);
        init(MESSAGES_BE_PROPERTIES);

        //email properties
        init(MAIL_PROPERTIES);
    }

    /**
     * Initializes properties map with .properties file located on given path in classpath.
     * Each call of this method initializes reading classpath file directory
     * and loading properties to app
     * regardless of were this properties in app or not.
     * It means if there were this properties,
     * they will be deleted from app and loaded again.
     *
     * @param name .properties file name
     */
    public void init(final String name) throws IOException {
        this.properties.remove(name);
        Properties properties = new Properties();

        InputStream in = PropertyLoader.class.getResourceAsStream("/" + name);
        if (in == null) {
            throw new IOException("Can't find file /" + name);
        }

        properties.load(new InputStreamReader(PropertyLoader.class.getResourceAsStream("/" + name), UTF_8));

        Map<String, String> propertiesFromFile = new HashMap<>();
        properties.forEach((key, value) -> propertiesFromFile.put((String) key, (String) value));

        if (this.properties.containsKey(name)) {
            properties.replace(name, unmodifiableMap(propertiesFromFile));
        } else {
            this.properties.put(name, unmodifiableMap(propertiesFromFile));
        }
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
        LOG.debug("Property in {} file with {} id is {}", filename, id, properties.get(filename).get(id));

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

    /**
     * Check if given string is integer.
     *
     * @param strNum string
     * @return true if integer, false otherwise
     */
    private boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
