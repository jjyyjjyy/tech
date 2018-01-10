package me.jy.visitor;

/**
 * @author jy
 * @date 2018/01/10
 */
public interface Visitor {

    void visit(Car car);

    void visit(Engine engine);
}
