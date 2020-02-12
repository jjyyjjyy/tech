package me.jy._21_interpreter;

import java.util.Map;

/**
 * @author jy
 */
public class MultiplySymbolExpression extends SymbolExpression {
    public MultiplySymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(Map<String, Integer> vars) {
        return left.interpret(vars) * right.interpret(vars);
    }
}
