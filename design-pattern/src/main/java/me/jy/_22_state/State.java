package me.jy._22_state;

/**
 * @author jy
 */
public abstract class State {

    protected final Activity activity;

    protected State(Activity activity) {
        this.activity = activity;
    }

    public abstract void deduceMoney();
    public abstract boolean raffle();
    public abstract void dispensePrize();
}
