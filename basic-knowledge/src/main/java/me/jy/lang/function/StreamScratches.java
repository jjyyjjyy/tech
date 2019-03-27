package me.jy.lang.function;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamScratches {

    private static void iterableToStream() {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
            .collect(Collectors.toList());

        StreamSupport.stream(list.spliterator(), true)
            .forEach(System.out::println);
    }

    public static void main(String[] args) {
        iterableToStream();
    }
}
