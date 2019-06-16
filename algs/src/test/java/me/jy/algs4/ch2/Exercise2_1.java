package me.jy.algs4.ch2;

import me.jy.algs4.CsvToArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jy
 */
class Exercise2_1 {

    @ParameterizedTest
    @CsvSource({"'3,2,1'", "'1,2,3,5,4'", "'2,4324,22,123,12,34,1232,3533,12332,54,344443'"})
    @DisplayName("SortTest")
    void sortTest(@ConvertWith(CsvToArray.class) int[] arr) {
        new Sorts.QuickSort().sort(arr);
        SortTemplate.ifSorted(arr);
    }
}
