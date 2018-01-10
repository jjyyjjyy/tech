package me.jy.observer;

import java.util.Random;

/**
 * @author jy
 * @date 2018/01/10
 */
public class OddNumberGenerator extends NumberGenerator {

    @Override
    protected Number doGenerate() {
        int number = new Random().nextInt(999);
        return number % 2 == 0 ? number + 1 : number;
    }
}
