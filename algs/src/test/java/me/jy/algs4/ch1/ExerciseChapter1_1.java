package me.jy.algs4.ch1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExerciseChapter1_1 {

    private static boolean checkEquals(int a, int b, int c) {
        return a == b && b == c;
    }

    private static boolean isInRange(double input) {
        return input >= 0 && input <= 1d;
    }

    @Test
    @DisplayName("1.1.1")
    void ex111() {
        assertEquals(7, (0 + 15) / 2);
        assertEquals(200.0000002, 2.0e-6 * 100000000.1);
        assertTrue(true && false || true && true);
    }

    @Test
    @DisplayName("1.1.2")
    void ex112() {
        assertEquals(1.618, (1 + 2.236) / 2);
        assertEquals(10.0, 1 + 2 + 3 + 4.0);
        assertTrue(4.1 >= 4);
        assertEquals("33", 1 + 2 + "3");
    }
    // end::ex1.1.3[]

    // tag::ex1.1.3[]
    @ParameterizedTest
    @CsvSource({"1,1,2,false", "11,11,11,true"})
    @DisplayName("1.1.3")
    void ex113(int a, int b, int c, boolean result) {
        assertEquals(result, checkEquals(a, b, c));
    }

    // tag::ex1.1.5[]
    @ParameterizedTest
    @CsvSource({"0.2d,0.5d,true", "0.2d,2d,false", "2d,1d,false"})
    void ex115(double inputX, double inputY, boolean result) {
        assertEquals(result, isInRange(inputX) && isInRange(inputY));
    }
    // end::ex1.1.5[]
}
