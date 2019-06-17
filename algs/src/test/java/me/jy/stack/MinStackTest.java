package me.jy.stack;

import me.jy.util.RandomArrayGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

/**
 * @author jy
 */
class MinStackTest {

    @ParameterizedTest
    @CsvSource({"10", "100", "1000"})
    void testGetMin1(@ConvertWith(RandomArrayGenerator.class) int[] arr) {
        MinStack minStack = new MinStackImpl1();
        for (int i = 0; i < arr.length; i++) {
            minStack.push(arr[i]);
            Assertions.assertEquals(Arrays.stream(Arrays.copyOf(arr, i + 1)).min().getAsInt(), minStack.getMin().intValue());
        }
    }

    @ParameterizedTest
    @CsvSource({"10", "100", "1000"})
    void testGetMin2(@ConvertWith(RandomArrayGenerator.class) int[] arr) {
        MinStack minStack = new MinStackImpl2();
        for (int i = 0; i < arr.length; i++) {
            minStack.push(arr[i]);
            Assertions.assertEquals(Arrays.stream(Arrays.copyOf(arr, i + 1)).min().getAsInt(), minStack.getMin().intValue());
        }
    }
}
