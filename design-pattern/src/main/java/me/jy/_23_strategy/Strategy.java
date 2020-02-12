package me.jy._23_strategy;

/**
 * @author jy
 */
public interface Strategy {

    Hand nextHand();

    void study(boolean win);
}
