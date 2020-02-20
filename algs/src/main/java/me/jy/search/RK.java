package me.jy.search;

/**
 * @author jy
 */
public class RK {

    public int search(String origin, String target) {
        if (origin.length() < target.length()) {
            return -1;
        }
        int hash = hash(target, 26, 31, 0, target.length());

        for (int i = 0; i < origin.length() - target.length() + 1; i++) {
            // 比较每一个子串的哈希值, 如果哈希值相同则比较两个子串是否完全相等.
            if (hash(origin, 26, 31, i, target.length()) == hash && match(origin, target, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean match(String origin, String target, int i) {
        for (int j = 0; j < target.length(); j++) {
            if (origin.charAt(i + j) != target.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算指定子串hash值
     */
    private int hash(String str, int r, int k, int start, int length) {
        int hash = 0;
        for (int i = start; i < start + length; i++) {
            hash = (r * hash + str.charAt(i) % k);
        }
        return hash;
    }

}
