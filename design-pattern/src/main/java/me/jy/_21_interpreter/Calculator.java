package me.jy._21_interpreter;

import java.util.Map;
import java.util.Stack;

/**
 * @author jy
 */
public class Calculator {

    private Expression expression;

    public Calculator(String expr) {
        Stack<Expression> expressions = new Stack<>();
        char[] chars = expr.toCharArray();

        Expression left;
        Expression right;

        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '+':
                    left = expressions.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    expressions.push(new AddSymbolExpression(left, right));
                    break;
                case '-':
                    left = expressions.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    expressions.push(new SubtractSymbolExpression(left, right));
                    break;
                case '*':
                    left = expressions.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    expressions.push(new MultiplySymbolExpression(left, right));
                    break;
                case '/':
                    left = expressions.pop();
                    right = new VarExpression(String.valueOf(chars[++i]));
                    expressions.push(new DivideSymbolExpression(left, right));
                    break;
                default:
                    expressions.push(new VarExpression(String.valueOf(chars[i])));
            }
        }
        expression = expressions.pop();
    }

    public int run(Map<String, Integer> vars) {
        return expression.interpret(vars);
    }
}
