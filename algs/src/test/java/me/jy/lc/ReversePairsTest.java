package me.jy.lc;

import me.jy.util.RandomArrayGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jy
 */
class ReversePairsTest {

    @ParameterizedTest
    @CsvSource({"10", "100", "1000", "1000"})
    void testReversePairs(@ConvertWith(RandomArrayGenerator.class) int[] arr) {
        ReversePairs reversePairs = new ReversePairs();
        Assertions.assertEquals(reversePairs.getReversePairs0(arr), reversePairs.getReversePairs(arr));
    }


}
