package me.jy.util;

import me.jy.core.server.ServerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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

    public static <T> T convertFileToObject(Class<T> type, Properties properties) {
        try {
            T instance = type.newInstance();
            Field[] declaredFields = type.getDeclaredFields();
            for (Field field : declaredFields) {
                String name = field.getName();
                properties.computeIfPresent(name, (k, v) -> {
                    try {
                        field.setAccessible(true);
                        Class<?> fieldType = field.getType();
                        if (fieldType == String.class) {
                            field.set(instance, v.toString());
                        } else {
                            field.set(instance, ClassUtils.castNumber(v, fieldType));
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return v;
                });
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot create instance from " + type);
    }

}
