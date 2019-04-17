package me.jy.algs4.ch1;

public class CircularShiftPredicate {

    public static boolean isCircularShifted(String origin, String target) {
        if (origin.length() != target.length()) {
            return false;
        }
        return (origin + origin).contains(target);
    }

}
