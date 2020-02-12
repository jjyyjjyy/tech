package me.jy._22_state;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {
        Activity activity = new Activity(10);

        for (int i = 0; i < 100; i++) {
            activity.deduceMoney();
            activity.raffle();
        }
    }
}
