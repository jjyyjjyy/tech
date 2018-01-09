package me.jy.strategy;

/**
 * @author jy
 * @date 2018/01/09
 */
public interface Strategy {

    Hand nextHand();

    void study(boolean win);
}
