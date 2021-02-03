package com.epam.xxlbet.milto.utils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * PropertyLoaderValidatorTest.
 *
 * @author Aliaksei Milto
 */
public class PropertyLoaderTest {
    private PropertyLoader propertyLoader = PropertyLoader.getInstance();

    @Test
    public void shouldSuccess() throws IOException {
        propertyLoader.init("test.properties");

        assertTrue(propertyLoader.getStringProperty("test.properties", "some.property").isPresent());
        assertEquals("1234", propertyLoader.getStringProperty("test.properties", "some.property").get());
    }

    @Test
    public void shouldSuccess_ButNoSuchProperty() throws IOException {
        propertyLoader.init("test.properties");

        assertTrue(propertyLoader.getStringProperty("test.properties", "some.property").isPresent());
        assertFalse(propertyLoader.getStringProperty("test.properties", "some.not.existing.property").isPresent());
    }

    @Test(expected = IOException.class)
    public void shouldThrowException_NoSuchFile() throws IOException {
        propertyLoader.init("test2.properties");
    }
}
