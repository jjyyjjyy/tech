package me.jy.visitor;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Engine implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
