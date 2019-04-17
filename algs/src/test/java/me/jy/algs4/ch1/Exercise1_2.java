package me.jy.algs4.ch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Exercise1_2 {

    @ParameterizedTest
    @CsvSource({"abc,bca,true", "aaaaab,aabaaa,true", "ADSF,FASD,false"})
    @DisplayName("1.2.6")
    void ex126(String origin, String target, boolean expect) {
        Assertions.assertEquals(expect, CircularShiftPredicate.isCircularShifted(origin, target));
    }

}
