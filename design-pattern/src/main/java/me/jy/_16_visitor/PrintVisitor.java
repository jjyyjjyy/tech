package me.jy._16_visitor;

/**
 * @author jy
 */
public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        System.out.println("I am visiting a car " + car.toString());
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("I am visiting an engine " + engine.toString());
    }
}
