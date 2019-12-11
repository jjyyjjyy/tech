package me.jy.lang.thread.cf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
class DiscountService {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final Integer percentage;

        Code(Integer percentage) {
            this.percentage = percentage;
        }

        public Integer getPercentage() {
            return percentage;
        }
    }


    static double getDiscountPrice(Quote quote) {
        delay();
        return BigDecimal
            .valueOf(quote.getDiscount() * quote.getPrice())
            .setScale(2, RoundingMode.HALF_DOWN)
            .doubleValue();
    }

    private static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
