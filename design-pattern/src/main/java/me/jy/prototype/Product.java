package me.jy.prototype;

/**
 * @author jy
 * @date 2018/01/09
 */
public class Product implements Cloneable {

    public Product() {
        System.out.println("start init...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("init over...");

    }

    public final Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }
}
