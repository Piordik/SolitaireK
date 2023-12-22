package main.java.database;

import java.io.IOException;
import java.io.InputStream;

public final class PropertiesFactory {
    private static DataBaseProperties properties;
    private PropertiesFactory() {
    }

    public synchronized static DataBaseProperties getProperties() {
        if (properties == null) {
            init();
        }
        return properties;
    }

    private static void init() {
        String filePropertiesName = "application.properties";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        properties = new DataBaseProperties();
        try (InputStream stream = classLoader.getResourceAsStream(filePropertiesName)) {
            properties.load(stream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }
}