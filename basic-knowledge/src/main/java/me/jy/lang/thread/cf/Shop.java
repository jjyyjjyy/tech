package me.jy.lang.thread.cf;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jy
 */
public class Shop {

    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble();
    }
}
