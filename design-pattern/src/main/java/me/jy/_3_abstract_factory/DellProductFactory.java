package me.jy._3_abstract_factory;

/**
 * @author jy
 */
public class DellProductFactory implements ProductFactory {
    @Override
    public Desktop createDesktop() {
        return new Desktop();
    }

    @Override
    public Laptop createLaptop() {
        return new Laptop();
    }
}
