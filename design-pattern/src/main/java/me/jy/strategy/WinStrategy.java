package me.jy.strategy;

import java.util.Random;

/**
 * @author jy
 * @date 2018/01/09
 */
public class WinStrategy implements Strategy {

    private Hand prev;

    private boolean won;

    public void setPrev(Hand prev) {
        this.prev = prev;
    }

    @Override
    public Hand nextHand() {
        if (won) {
            return prev;
        }
        return new Hand(new Random().nextInt(3));
    }

    @Override
    public void study(boolean win) {
        this.won = win;
    }
}
