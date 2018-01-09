package me.jy.prototype;

/**
 * @author jy
 * @date 2018/01/09
 */
public class Main {

    public static void main(String[] args) {
        NamedProduct product = new NamedProduct();
        product.setName("Bobi");
        PrototypeManager<Product> pm = new PrototypeManager<>(product);

        for (int i = 0; i < 100; i++) {
            System.out.println(pm.create());
        }

    }

}
