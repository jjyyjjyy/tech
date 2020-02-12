package me.jy._21_interpreter;

import java.util.Map;

/**
 * 变量解释器
 *
 * @author jy
 */
public class VarExpression implements Expression {

    private final String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpret(Map<String, Integer> vars) {
        return vars.get(key);
    }
}
