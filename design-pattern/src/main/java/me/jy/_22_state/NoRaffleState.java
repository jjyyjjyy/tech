package me.jy._22_state;

/**
 * @author jy
 */
public class NoRaffleState extends State {

    protected NoRaffleState(Activity activity) {
        super(activity);
    }

    @Override
    public void deduceMoney() {
        System.out.println("deduce 50 bucks");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        return false;
    }

    @Override
    public void dispensePrize() {

    }
}
