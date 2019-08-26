package me.jy.sensor;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class SensitiveWordMaskDecorator implements SensitiveWordDecorator {

    @Override
    public String decorate(char[] words, List<Range> sensorRanges) {
        sensorRanges.forEach(range -> IntStream.rangeClosed(range.getStart(), range.getEnd()).forEach(i -> words[i] = '*'));
        return new String(words);
    }
}
