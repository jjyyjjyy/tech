package me.jy.lang.thread.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class ArrayListProblem {

    public static void main(String[] args) {
        List<Integer> unsafeList = new ArrayList<>(10);
        IntStream.rangeClosed(1, 1000)
            .parallel()
            .forEach(unsafeList::add);
        System.out.println(unsafeList.size());

        List<Integer> safeList = new CopyOnWriteArrayList<>();

        IntStream.rangeClosed(1, 1000)
            .parallel()
            .forEach(safeList::add);
        System.out.println(safeList.size());

    }
}
