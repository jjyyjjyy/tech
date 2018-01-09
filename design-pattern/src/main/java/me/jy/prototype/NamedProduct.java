package me.jy.prototype;

/**
 * @author jy
 * @date 2018/01/09
 */
public class NamedProduct extends Product{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NamedProduct{" +
                "name='" + name + '\'' +
                '}';
    }
}
