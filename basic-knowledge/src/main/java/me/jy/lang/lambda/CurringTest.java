package me.jy.lang.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

public class CurringTest {
    public static void main(String[] args) {
        IntFunction<Integer> currPrice = curring(items ->
                Long.valueOf(items.stream()
                        .mapToLong(Long::valueOf)
                        .sum())
                        .intValue()
        );

        currPrice.apply(1);
        currPrice.apply(2);
        int result = currPrice.apply(Integer.MAX_VALUE);
        System.out.println(result);
    }

    private static IntFunction<Integer> curring(Function<List<Integer>, Integer> fn) {
        final List<Integer> result = new ArrayList<>(); // copy in currPrice

        return arg -> {
            if (arg != Integer.MAX_VALUE) {
                result.add(arg);
            } else {
                //当输入INT最大值时进行计算
                return fn.apply(result);
            }
            return null;
        };
    }
}