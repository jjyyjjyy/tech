package me.jy._22_state;

/**
 * @author jy
 */
public class OutOfDispenseState extends State {

    protected OutOfDispenseState(Activity activity) {
        super(activity);
    }

    @Override
    public void deduceMoney() {

    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrize() {

    }
}
