package me.jy.visitor;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Main {

    public static void main(String[] args) {

        Visitor visitor = new PrintVisitor();
        Car car = new Car();
        Engine engine = new Engine();
        visitor.visit(car);
        visitor.visit(engine);

    }
}
