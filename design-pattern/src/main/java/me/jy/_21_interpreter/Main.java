package me.jy._21_interpreter;

import java.util.Map;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {

        Calculator calculator = new Calculator("a*b+c");

        Map<String, Integer> vars = Map.of("a", 1, "b", 2, "c", 3);

        System.out.println(calculator.run(vars));
    }
}
