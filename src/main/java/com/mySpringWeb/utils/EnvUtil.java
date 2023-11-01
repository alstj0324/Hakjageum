package com.mySpringWeb.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

public class EnvUtil {
    private final Properties properties;

    public EnvUtil() {
        this.properties = new Properties();

        try {
            properties.load(EnvUtil.class.getClassLoader().getResource("env.properties").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValueByKey(String key) {
        return this.properties.getProperty(key);
    }

    public Set<String> getKeyList() {
        return this.properties.stringPropertyNames();
    }

    public boolean editPropertyValueByKey(String key, String value) {
        return this.properties.replace(key, value) != null;
    }

    public boolean removePropertyByKey(String key) {
        return this.properties.remove(key) != null;
    }

    public void savePropertiesFile(String path) {
        try {
            this.properties.store(Files.newOutputStream(Paths.get(path + "_new")), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isKeyExist(String key) {
        return this.properties.containsKey(key);
    }

    public boolean addProperty(String key, String value) {
        return this.properties.putIfAbsent(key, value) != null;
    }
}
