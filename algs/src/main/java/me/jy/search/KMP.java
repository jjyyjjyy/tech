package me.jy.search;

/**
 * @author jy
 */
public class KMP {

    public int search(String origin, String target) {

        int[] next = next(target);

        int i = 0;
        int j = 0;
        while (i < origin.length()) {
            while (j > 0 && target.charAt(j) != origin.charAt(i)) {
                j = next[j - 1];
            }
            if (target.charAt(j) == origin.charAt(i)) {
                j++;
            }
            i++;
            if (j == target.length()) {
                return i - j;
            }
        }
        return -1;
    }

    private int[] next(String target) {
        int[] next = new int[target.length()];

        next[0] = 0;

        for (int i = 1, k = 0; i < target.length(); i++) {
            while (k > 0 && target.charAt(i) != target.charAt(k)) {
                k = next[k - 1];
            }
            if (target.charAt(i) == target.charAt(k)) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }
}
