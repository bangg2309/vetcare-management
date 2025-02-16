package vn.edu.hcmuaf.vetcaremanagement.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Slf4j
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                log.error("Not found file application.properties");
                throw new RuntimeException("Not found file application.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            log.error("Failed to load configuration", ex);
            throw new RuntimeException("Failed to load configuration", ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}