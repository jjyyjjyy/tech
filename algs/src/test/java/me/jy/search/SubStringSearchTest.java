package me.jy.search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 */
public class SubStringSearchTest {

    private final BF bf = new BF();

    private final RK rk = new RK();

    private final KMP kmp = new KMP();

    @ParameterizedTest
    @CsvSource({"'abc','vc',-1", "'abcdefg','fg',5", "'dsaawdwa','aa',2"})
    void search(String origin, String target, int index) {
        assertEquals(index, bf.search(origin, target));
        assertEquals(index, rk.search(origin, target));
        assertEquals(index, kmp.search(origin, target));
    }
}
