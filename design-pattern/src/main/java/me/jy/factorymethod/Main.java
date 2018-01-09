package me.jy.factorymethod;

/**
 * @author jy
 * @date 2018/01/09
 */
public class Main {

    public static void main(String[] args) {
        Factory factory = new ICardFactory();
        Product product = factory.create("ICBC");
        product.use();
    }
}
