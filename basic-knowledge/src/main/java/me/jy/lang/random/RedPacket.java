package me.jy.lang.random;

import java.math.BigDecimal;
import java.util.Random;

import static java.math.RoundingMode.HALF_DOWN;

/**
 * @author jy
 * @date 2018/01/28
 */
public class RedPacket {

    private int num;

    private double balance;

    private static final Random RANDOM = new Random();

    /**
     * 设置红包
     *
     * @param balance 红包额
     * @param num     人数
     */
    public RedPacket(double balance, int num) {
        this.balance = balance;
        this.num = num;
    }

    public double nextPacket() {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            num--;
            return setScale(balance);
        }
        double max = balance / num * 2;
        double result = Math.min(max,
                Math.max(0.01, setScale(max * RANDOM.nextDouble())));
        balance -= result;
        num--;
        return result;
    }

    private static double setScale(double val) {
        return BigDecimal.valueOf(val)
                .setScale(2, HALF_DOWN)
                .doubleValue();
    }

    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket(10.00, 5);
        for (int i = 0; i < 10; i++) {
            System.out.println(redPacket.nextPacket());
        }
    }

}
