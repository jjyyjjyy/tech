package me.jy.algs4.ch1;

import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author jy
 */
public class Evaluator {

    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";

    private static final Pattern OPERATOR_REGEX = Pattern.compile("[+\\-*/]");

    private final String expression;

    public Evaluator(String exp) {
        this.expression = exp.replaceAll("\\s", "");
    }

    public int getResult() {

        Stack<Integer> operandStack = new Stack<>();
        Stack<Operator> operatorStack = new Stack<>();

        Arrays.stream(expression.split(""))
            .filter(s -> !LEFT_PARENTHESIS.equals(s))
            .forEach(s -> {
                if (RIGHT_PARENTHESIS.equals(s)) {
                    Operator operator = operatorStack.pop();
                    Integer b = operandStack.pop();
                    Integer a = operandStack.pop();
                    operandStack.push(operator.op(a, b));
                } else if (OPERATOR_REGEX.matcher(s).matches()) {
                    operatorStack.push(Operator.of(s));
                } else {
                    operandStack.push(Integer.valueOf(s));
                }
            });

        return operandStack.pop();

    }


    private enum Operator {

        ADD("+") {
            @Override
            public int op(int a, int b) {
                return a + b;
            }
        },
        MINUS("-") {
            @Override
            public int op(int a, int b) {
                return a - b;
            }
        },
        MULTI("*") {
            @Override
            public int op(int a, int b) {
                return a * b;
            }
        },
        DIV("/") {
            @Override
            public int op(int a, int b) {
                return a / b;
            }
        };

        private final String operator;

        Operator(String operator) {
            this.operator = operator;
        }

        static Operator of(String s) {
            Operator[] enumConstants = Operator.class.getEnumConstants();
            for (Operator operator : enumConstants) {
                if (s.equals(operator.operator)) {
                    return operator;
                }
            }
            return null;
        }

        public abstract int op(int a, int b);

    }
}
