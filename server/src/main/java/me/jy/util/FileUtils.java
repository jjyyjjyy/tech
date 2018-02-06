package me.jy.util;

import me.jy.core.ServerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jy
 * @date 2018/02/06
 */
public class FileUtils {

    private FileUtils() {
    }

    public static Properties getFileProperties(String fileName) {
        InputStream inputStream = ServerFactory.class.getClassLoader().getResourceAsStream(fileName);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static <T> T getValue(Properties properties, String key, T defaultValue, Class<T> type) {
        return ClassUtils.castNumber(properties.getOrDefault(key, defaultValue), type);
    }

}
