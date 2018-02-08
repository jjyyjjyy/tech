package me.jy.util;

import lombok.NonNull;

import java.lang.reflect.ParameterizedType;

/**
 * @author jy
 * @date 2018/02/06
 */
@SuppressWarnings("unchecked")
public class ClassUtils {

    private ClassUtils() {
    }

    public static <T> T castNumber(@NonNull Object raw, @NonNull Class<T> targetType) {

        Class<?> rawClass = raw.getClass();
        if (rawClass.isAssignableFrom(targetType)) {
            return (T) raw;
        }

        String rawValue = raw.toString();
        if (targetType == Float.class || targetType == Double.class) {
            return (T) Double.valueOf(rawValue);
        }
        if (targetType.isPrimitive() || Number.class.isAssignableFrom(targetType)) {
            if (targetType == Integer.class || targetType == int.class)
                return (T) ((Integer.valueOf(rawValue)));
            if (targetType == Long.class || targetType == long.class)
                return (T) ((Long.valueOf(rawValue)));
            if (targetType == Byte.class || targetType == byte.class)
                return (T) ((Byte.valueOf(rawValue)));
            if (targetType == Short.class || targetType == short.class)
                return (T) ((Short.valueOf(rawValue)));
        }
        return null;
    }

    public static Class<?> getGenericType(Class<?> clazz) {
        ParameterizedType type = (ParameterizedType) clazz.getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }

}
