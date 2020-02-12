package me.jy._16_visitor;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {

        Visitor visitor = new PrintVisitor();
        Car car = new Car();
        Engine engine = new Engine();
        visitor.visit(car);
        visitor.visit(engine);

    }
}
