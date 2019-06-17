package me.jy.util;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

/**
 * @author jy
 */
public class RandomArrayGenerator extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        return ArrayUtil.getRandomArrayWithBound(Integer.valueOf(source.toString()));
    }
}
