package me.jy._21_interpreter;

import java.util.Map;

/**
 * @author jy
 */
public interface Expression {

    int interpret(Map<String, Integer> vars);
}
