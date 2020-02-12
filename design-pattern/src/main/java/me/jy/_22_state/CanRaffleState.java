package me.jy._22_state;

/**
 * @author jy
 */
public class CanRaffleState extends State {

    protected CanRaffleState(Activity activity) {
        super(activity);
    }

    @Override
    public void deduceMoney() {
    }

    @Override
    public boolean raffle() {
        System.out.println("抽奖中...");
        boolean raffle = Math.random() > 0.5;
        if (raffle) {
            System.out.println("恭喜中奖!");
            activity.setState(activity.getDispenseState());
            return true;
        }else {
            System.out.println("没有抽中 :(");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {

    }
}
