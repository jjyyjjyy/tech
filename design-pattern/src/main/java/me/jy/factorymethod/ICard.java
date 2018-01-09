package me.jy.factorymethod;

/**
 * @author jy
 * @date 2018/01/09
 */
public abstract class ICard implements Product {
    @Override
    public void use() {
        System.out.println("I am using a " + getType() + " ICard");
    }

    protected abstract String getType();
}
