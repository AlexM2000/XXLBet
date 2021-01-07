package com.epam.xxlbet.milto.utils;

import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

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
    public void shouldFail_NoSuchFile() throws IOException {
        propertyLoader.init("test2.properties");
    }
}
