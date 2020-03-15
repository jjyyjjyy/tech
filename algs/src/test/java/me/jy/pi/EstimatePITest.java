package me.jy.pi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jy
 */
@Slf4j
class EstimatePITest {

    @ParameterizedTest
    @CsvSource({"1000", "10000", "100000", "1000000", "10000000", "100000000"})
    void testEstimate(int count) {
        double estimate = new EstimatePI().estimate(count);
        log.info("PI: {}", estimate);
        Assertions.assertTrue(estimate > 3 && estimate < 4);
    }
}
