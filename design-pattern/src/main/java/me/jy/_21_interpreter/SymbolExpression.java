package me.jy._21_interpreter;

/**
 * @author jy
 */
public abstract class SymbolExpression implements Expression {

    protected final Expression left;
    protected final Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

}
