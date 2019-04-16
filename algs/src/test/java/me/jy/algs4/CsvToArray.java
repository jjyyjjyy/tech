package me.jy.algs4;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author jy
 */
public class CsvToArray extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        String[] strings = source.toString().split("\\s*,\\s*");
        if (Objects.equals(targetType, int[].class)) {
            return Arrays.stream(strings).mapToInt(Integer::valueOf).toArray();
        }
        return strings;
    }
}
