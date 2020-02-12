package me.jy._22_state;

/**
 * @author jy
 */
public class RaffleState extends State {

    protected RaffleState(Activity activity) {
        super(activity);
    }

    @Override
    public void deduceMoney() {

    }

    @Override
    public boolean raffle() {
        return true;
    }

    @Override
    public void dispensePrize() {

    }
}
