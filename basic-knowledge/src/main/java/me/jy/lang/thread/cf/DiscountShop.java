package me.jy.lang.thread.cf;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class DiscountShop {

    private final String name;

    public DiscountShop(String name) {
        this.name = name;
    }

    String getPrice() {
        return String.join(":", getName(),
                "" + getPriceInternal(),
                "" + DiscountService.Code.values()[ThreadLocalRandom.current().nextInt(5)].getPercentage());
    }

    public String getName() {
        return name;
    }

    private double getPriceInternal() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble();
    }
}
