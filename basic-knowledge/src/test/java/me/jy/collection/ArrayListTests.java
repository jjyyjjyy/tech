package me.jy.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ArrayList:允许空元素,有序,允许重复元素,非线程安全
 *
 * @author jy
 * @date 2017/11/07
 */
public class ArrayListTests {

    @Test
    void testToArray() {
        Object[] objects = Arrays.asList(1, 2, 3).toArray();
        assertEquals("Object[]", objects.getClass().getSimpleName());

//        objects[0] = "a";//ArrayStoreException

        Integer[] objArr = {2, 3, 4};
        assertEquals("Integer[]", objArr.getClass().getSimpleName());

        Object[] intArr = objArr;

        //ArrayStoreException,能存的类型看raw type
//        intArr[1] = "a";
        assertEquals("Integer[]", intArr.getClass().getSimpleName());
    }

    @Test
    void testItrBug() {
        List<Integer> demoList = Stream.of(1, 2, 3).collect(Collectors.toList());
        for (Integer element : demoList) {
            if (element == 2) {
                demoList.remove(1); // ConcurrentModificationException will not be called when leaving only one element to iterate
            }
        }
    }
}
