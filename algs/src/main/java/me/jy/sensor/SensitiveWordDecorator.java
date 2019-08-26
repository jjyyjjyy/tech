package me.jy.sensor;

import java.util.List;

/**
 * @author jy
 */
public interface SensitiveWordDecorator {

    default String decorate(char[] words, List<Range> sensorRanges) {
        return new String(words);
    }
}
