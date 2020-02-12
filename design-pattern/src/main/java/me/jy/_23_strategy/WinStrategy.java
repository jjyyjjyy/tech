package me.jy._23_strategy;

import java.util.Random;

/**
 * @author jy
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
