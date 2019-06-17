package me.jy.sort;

import me.jy.util.ArrayUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author jy
 */
class SortTests {

    static Stream<Arguments> sortTest() {

        return Stream.of(Sorts.class.getDeclaredClasses()).map(c -> {
            try {
                return (SortTemplate) c.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).flatMap(s -> Stream.of(10, 100, 1000, 10_000, 100_000).map(count -> Arguments.of(count, s.getClass().getSimpleName(), s, ArrayUtil.getRandomArray(count))));
    }

    @ParameterizedTest(name = "[{0}] {1}")
    @MethodSource
    void sortTest(int size, String className, SortTemplate sortTemplate, int[] arr) {
        sortTemplate.sort(arr);
        SortTemplate.ifSorted(arr);
    }

}
