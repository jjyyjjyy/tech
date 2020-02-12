package me.jy._22_state;

import lombok.Data;

/**
 * 不能抽奖 -> 可以抽奖 -> 点击抽奖 -> 奖品领完
 * @author jy
 */
@Data
public class Activity {


    private State canRaffleState = new CanRaffleState(this);
    private State raffleState = new RaffleState(this);
    private State dispenseState = new DispenseState(this);
    private State outOfDispenseState = new OutOfDispenseState(this);
    private State noRaffleState = new NoRaffleState(this);

    private State state = noRaffleState;

    // 奖品数量
    private int count;

    public Activity(int count) {
        this.count = count;
    }

    public void deduceMoney() {
        state.deduceMoney();
    }

    public void raffle() {
        if (state.raffle()) {
            state.dispensePrize();
        }
    }

}
