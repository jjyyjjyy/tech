package me.jy._16_visitor;

/**
 * @author jy
 */
public class Car implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
