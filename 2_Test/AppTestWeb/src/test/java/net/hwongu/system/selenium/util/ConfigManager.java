package net.hwongu.system.selenium.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fileInput = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading config.properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
