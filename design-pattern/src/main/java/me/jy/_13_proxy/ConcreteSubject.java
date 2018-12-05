package me.jy._13_proxy;

/**
 * @author jy
 */
public class ConcreteSubject implements Subject {
    @Override
    public void request() {
        System.out.println(getClass().getSimpleName());
    }
}
