package me.jy.lc;

import me.jy.util.RandomArrayGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jy
 */
class MinSumTest {

    @ParameterizedTest
    @CsvSource({"10", "100", "1000", "10000"})
    void testMinSum(@ConvertWith(RandomArrayGenerator.class) int[] arr) {

        MinSum minSum = new MinSum();

        Assertions.assertEquals(minSum.getMinSum0(arr), minSum.getMinSum(arr));
    }

}
