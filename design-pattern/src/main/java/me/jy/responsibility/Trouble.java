package me.jy.responsibility;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Trouble {

    private final int number;

    public Trouble(int number) {
        this.number = number;
    }

    public final int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Trouble[" + this.number + "]";
    }
}
