package me.jy._23_strategy;

/**
 * @author jy
 */
public class Player {

    private final Strategy strategy;

    private final String name;

    public Player(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public Hand nextHand() {
        return strategy.nextHand();
    }

    public void win() {
        strategy.study(true);
    }

    public void lose() {
        strategy.study(false);
    }
}
