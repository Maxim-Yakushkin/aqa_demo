package com.yakushkin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final String APPLICATION_PROPERTY_FILE = "/application.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        initProperties();
    }

    public static String getProperty(String propertyKey) {
        return PROPERTIES.getProperty(propertyKey);
    }

    private static void initProperties() {
        try (InputStream inputStream = PropertiesReader.class.getResourceAsStream(PropertiesReader.APPLICATION_PROPERTY_FILE)) {
            PropertiesReader.PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load properties from file " +
                                               PropertiesReader.APPLICATION_PROPERTY_FILE);
        }
    }
}
