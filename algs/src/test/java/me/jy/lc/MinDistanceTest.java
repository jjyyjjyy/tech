package me.jy.lc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jy
 */
class MinDistanceTest {

    @ParameterizedTest
    @CsvSource("'1 2,3 4,5 6,7 8,9 10,2 3,23 5,2 1,4 1,6 1,3 5,16 2'")
    void getMinDistance(@ConvertWith(PointArgumentResolver.class) List<MinDistance.Point> points) {

        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                minDistance = Math.min(minDistance, MinDistance.getDistance(points.get(i), points.get(j)));
            }
        }

        Assertions.assertEquals(minDistance, MinDistance.getMinDistance(points));

    }

    public static class PointArgumentResolver extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {

            return Arrays.stream(source.toString().split(","))
                .map(s -> {
                    String[] split = s.split("\\s");
                    return new MinDistance.Point(Double.valueOf(split[0]), Double.valueOf(split[1]));
                })
                .collect(Collectors.toList());

        }
    }

}
