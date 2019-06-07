package ru.javarush.intership.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(is);
        } catch (IOException e) {
            logger.error("Couldn't load properties file app.properties", e);
        }
    }

    public static int getPort() {
        String propertyPort = props.getProperty("port");
        if (propertyPort == null || propertyPort.isEmpty() || !propertyPort.matches("\\d+")) {
            logger.warn("Some problem with app.properties file. Port set to 8080");
            return 8080;
        }
        return Integer.valueOf(propertyPort);
    }
}
