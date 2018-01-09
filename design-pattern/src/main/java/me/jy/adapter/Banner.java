package me.jy.adapter;

/**
 * @author jy
 * @date 2018/01/06
 */
public class Banner {

    private final String ad;

    public Banner(String ad) {
        this.ad = ad;
    }

    public void showWithParen() {
        System.out.println("(" + this.ad + ")");
    }

    public void showWithAster() {
        System.out.println("*" + this.ad + "*");
    }

    @Override
    public String toString() {
        return this.ad;
    }
}
