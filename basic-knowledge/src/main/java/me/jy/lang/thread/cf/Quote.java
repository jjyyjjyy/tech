package me.jy.lang.thread.cf;

import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class Quote {

    private final String name;

    private Integer discount;

    private double price;

    public Quote(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Quote parse(String priceStr) {
        String[] split = priceStr.split(":");
        Quote q = new Quote(split[0]);
        q.setPrice(Double.valueOf(split[1]));
        q.setDiscount(Integer.valueOf(split[2]));
        return q;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
