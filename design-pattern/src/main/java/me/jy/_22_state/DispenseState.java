package me.jy._22_state;

/**
 * @author jy
 */
public class DispenseState extends State {

    protected DispenseState(Activity activity) {
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
        if (activity.getCount() > 0) {
            System.out.println("发奖品!");
            activity.setCount(activity.getCount() - 1);
            activity.setState(activity.getNoRaffleState());
        }
        else {
            System.out.println("奖品发完了");
            System.exit(0);
        }
    }
}
