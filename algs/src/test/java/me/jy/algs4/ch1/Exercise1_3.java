package me.jy.algs4.ch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jy
 */
class Exercise1_3 {

    @ParameterizedTest
    @CsvSource({"'(1+((2+3)*(4*5)))',101"})
    void testEvaluator(String expression, int result) {
        Assertions.assertEquals(result, new Evaluator(expression).getResult());
    }
}
