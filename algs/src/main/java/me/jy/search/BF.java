package me.jy.search;

/**
 * @author jy
 */
public class BF {

    public int search(String origin, String target) {
        if (origin.length() < target.length()) {
            return -1;
        }
        char[] originChars = origin.toCharArray();
        char[] targetChars = target.toCharArray();

        // 移动双指针来比较两个子串.
        int o = 0, t = 0;
        while (o < originChars.length && t < targetChars.length) {
            if (originChars[o] == targetChars[t]) {
                o++;
                t++;
            } else {
                o = o - t + 1;
                t = 0;
            }
        }

        if (t == targetChars.length) {
            return o - t;
        }
        return -1;
    }
}
