package me.jy.algs4.ch2;

import me.jy.algs4.CsvToArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jy
 */
class Exercise2_1 {

    private static final List<SortTemplate> SORT_TEMPLATES = new ArrayList<>();

    static {
        Arrays.stream(Sorts.class.getDeclaredClasses())
            .map(c -> {
                try {
                    return (SortTemplate) c.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .forEach(SORT_TEMPLATES::add);

    }

    @ParameterizedTest
    @CsvSource({"'1,2,3,5,4'"})
    @DisplayName("SortTest")
    void sortTest(@ConvertWith(CsvToArray.class) int[] arr) {
        SORT_TEMPLATES.forEach(s -> {
            s.sort(arr);
            SortTemplate.ifSorted(arr);
        });
    }
}
