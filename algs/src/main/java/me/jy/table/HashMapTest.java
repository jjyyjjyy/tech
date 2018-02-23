package me.jy.table;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 * @date 2018/02/22
 */
public class HashMapTest {

    private static final Map<Integer, Integer> MAP = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool
                    .execute(() -> {
                        for (int j = 0; j < 200; j++) {
                            MAP.put(j, j);
                            if (!MAP.containsKey(0)) {
                                System.out.println("err!");
                            }
                        }
                    });
        }

        pool.shutdown();
        HashMap<Object, Integer> mapDebugger = new HashMap<>(8);
        mapDebugger.put(null, 22);
        mapDebugger.put(0, 22);
        System.out.println(mapDebugger.size());// 2

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);

        for (Integer i : integers) {
            integers.remove(i);
            System.out.println(integers.size());
            // cursor == size == 1  => break; ConcurrentModificationException will not happen here!
        }

        System.out.println(integers);

        HashMap<Object, Integer> linkedHashMapDebugger = new LinkedHashMap<>();
        linkedHashMapDebugger.put(0, 22);
        linkedHashMapDebugger.put(null, 22);
        System.out.println(linkedHashMapDebugger.size());// 2


        Map<ErrorKey, String> errorMap = new HashMap<>();
        errorMap.put(new ErrorKey(1), "a");
        errorMap.put(new ErrorKey(3), "b");
        errorMap.put(new ErrorKey(5), "c");
        errorMap.forEach((k, v) -> System.out.println(k + ":" + v));


    }


    private static class ErrorKey {

        private final int value;

        private ErrorKey(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            int i = new Random().nextInt(10);
            System.out.println("hashCode=" + i);
            return i;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }
}
