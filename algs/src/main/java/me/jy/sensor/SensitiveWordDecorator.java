package me.jy.sensor;

/**
 * @author jy
 */
public abstract class SensitiveWordDecorator {

    private final SensitiveWordDecorator decorator;

    protected SensitiveWordDecorator(SensitiveWordDecorator decorator) {
        this.decorator = decorator;
    }

    public void decorate(String words) {

    }
}
