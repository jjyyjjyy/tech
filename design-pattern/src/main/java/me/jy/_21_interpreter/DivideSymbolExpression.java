package me.jy._21_interpreter;

import java.util.Map;

/**
 * @author jy
 */
public class DivideSymbolExpression extends SymbolExpression {
    public DivideSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(Map<String, Integer> vars) {
        return left.interpret(vars) / right.interpret(vars);
    }
}
