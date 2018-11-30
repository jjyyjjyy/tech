package me.jy._9_composite;

/**
 * @author jy
 */
public abstract class Component {

    private String name;

    protected Component(String name) {
        this.name = name;
    }

    protected abstract void render();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
