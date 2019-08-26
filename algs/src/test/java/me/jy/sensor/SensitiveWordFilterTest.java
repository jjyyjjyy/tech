package me.jy.sensor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jy
 */
class SensitiveWordFilterTest {

    private static SensitiveWordFilter filter;

    @BeforeAll
    static void init() {
        filter = new SensitiveWordFilter(() -> Stream.of("你是", "sb").collect(Collectors.toSet()),
            new SensitiveWordMaskDecorator());
    }

    @ParameterizedTest
    @CsvSource({"'我是你','我是你'", "'你是我','**我'", "'你是sb吗', '****吗'", "'s你是','s**'"})
    void decorate(String origin, String result) {
        Assertions.assertEquals(result, filter.decorate(origin));
    }
}
