package me.jy.algs4.ch1;

import edu.princeton.cs.algs4.Counter;
import me.jy.util.CsvToArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Exercise1_2 {

    @ParameterizedTest
    @CsvSource({"abc,bca,true", "aaaaab,aabaaa,true", "ADSF,FASD,false"})
    @DisplayName("1.2.6")
    void ex126(String origin, String target, boolean expect) {
        assertEquals(expect, CircularShiftPredicate.isCircularShifted(origin, target));
    }

    // tag::ex1.2.9[]
    @Test
    @DisplayName("1.2.9")
    void ex129() {
        Counter counter = new Counter("BinarySearchCounter");
        rank(new int[]{1, 23}, 1, counter);
        assertEquals(1, counter.tally());
    }

    private static int rank(int[] arr, int target, Counter counter) {

        int start = 0;
        int end = arr.length - 1;

        if (end == 0 || arr[start] > target || arr[end] < target) {
            return -1;
        }

        while (true) {
            counter.increment();
            int mid = (start + end) / 2;
            int midValue = arr[mid];
            if (midValue == target) {
                return mid;
            }
            if (midValue < target) {
                start = mid + 1;
            } else end = mid - 1;
        }
    }
    // end::ex1.2.9[]

    @Test
    @DisplayName("1.2.11")
    void ex1211() {
        assertDoesNotThrow(() -> new SmartDate(2019, 2, 22));
        assertDoesNotThrow(() -> new SmartDate(2020, 2, 29));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(2019, 2, 29));
    }

    @ParameterizedTest
    @CsvSource({"2000,1,2,SUNDAY", "2019,4,21,SUNDAY"})
    @DisplayName("1.2.12")
    void ex1212(int year, int month, int day, SmartDate.Week week) {
        assertEquals(new SmartDate(year, month, day).dayOfWeek(), week);
    }

    // tag::ex1.2.15[]
    @ParameterizedTest
    @CsvSource({"'1 2 3 4 5','1,2,3,4,5'"})
    @DisplayName("1.2.15")
    void ex1215(String source, @ConvertWith(CsvToArray.class) int[] expect) {
        assertArrayEquals(expect, readInts(source));
    }

    private static int[] readInts(String source) {
        return Stream
            .of(source)
            .flatMap(s -> Arrays.stream(s.split("\\s")))
            .mapToInt(Integer::valueOf)
            .toArray();
    }
    // end::ex1.2.15[]

    // tag::ex1.2.16[]
    @ParameterizedTest
    @CsvSource({"'2,1','3,1','5,1'", "'10,2','3,4','23,4'"})
    @DisplayName("1.2.16")
    void ex1216(Rational a, Rational b, Rational expect) {
        assertEquals(expect, a.plus(b));
    }
    // end::ex1.2.16[]

}
