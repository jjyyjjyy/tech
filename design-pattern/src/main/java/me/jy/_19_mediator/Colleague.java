package me.jy._19_mediator;

/**
 * @author jy
 */
public abstract class Colleague {

    protected final Mediator mediator;
    protected final String name;

    protected Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public void contact(String message) {
        mediator.contact(message, this);
    }
    public void getMessage(String message) {
        System.out.println(name + "获得消息: " + message);
    }
}
