package me.jy._23_strategy;

/**
 * @author jy
 */
public class Hand {

    private static final int GUU = 0; // 石头
    private static final int CHO = 1; // 剪刀
    private static final int PAA = 2; // 布

    private final int value;

    public Hand(int value) {
        this.value = value;
    }

    public int match(Hand anotherHand) {
        if (this.equals(anotherHand))
            return 0;//平局
        if ((this.value + 1) % 3 == anotherHand.getValue()) {
            return 1;// 胜
        }
        return -1; // 负
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hand && this.value == Hand.class.cast(obj).getValue();
    }
}
