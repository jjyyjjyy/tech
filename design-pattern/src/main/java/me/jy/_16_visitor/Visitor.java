package me.jy._16_visitor;

/**
 * @author jy
 */
public interface Visitor {

    void visit(Car car);

    void visit(Engine engine);
}
