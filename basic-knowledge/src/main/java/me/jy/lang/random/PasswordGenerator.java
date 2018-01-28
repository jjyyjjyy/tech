package me.jy.lang.random;

import java.util.Arrays;
import java.util.Random;

/**
 * 随机密码生成工具
 *
 * @author jy
 * @date 2018/01/28
 */
public class PasswordGenerator {

    private static final char[] SYMBOLS = {'=', '@', '#', '$', '%', '^', '&', '*', '+', '_'};

    private static final Random RANDOM = new Random();

    private static final char EMPTY = (char) 12;

    private static final PasswordStrategy DEFAULT_PASSWORD_STRATEGY = new PasswordStrategy();

    public static String getRandomPassword() {
        return getRandomPassword(DEFAULT_PASSWORD_STRATEGY);
    }

    public static String getRandomPassword(PasswordStrategy passwordStrategy) {

        int length = passwordStrategy.getLength();
        int minSymbol = passwordStrategy.getMinSymbol();
        int minLowChar = passwordStrategy.getMinLowChar();
        int minUpperChar = passwordStrategy.getMinUpperChar();
        int minDigest = passwordStrategy.getMinDigest();
        char[] pwd = new char[length];
        Arrays.fill(pwd, EMPTY);
        int currentStart = 0;
        int currentEnd = minSymbol;
        for (int i = currentStart; i < minSymbol; i++) {
            pwd[i] = getRandomSymbol();
        }
        currentStart = currentEnd;
        currentEnd = currentStart + minLowChar;
        for (int i = currentStart; i < currentEnd; i++) {
            pwd[i] = getRandomLowChar();
        }
        currentStart = currentEnd;
        currentEnd = currentStart + minUpperChar;
        for (int i = currentStart; i < currentEnd; i++) {
            pwd[i] = getRandomUpperChar();
        }

        currentStart = currentEnd;
        currentEnd = currentStart + minDigest;
        for (int i = currentStart; i < currentEnd; i++) {
            pwd[i] = getRandomDigestChar();
        }

        for (int i = currentEnd; i < length; i++) {
            pwd[i] = getRandomLowChar();
        }
        shuffle(pwd);
        return new String(pwd);
    }

    private static char getRandomSymbol() {
        return SYMBOLS[RANDOM.nextInt(10)];
    }

    private static char getRandomLowChar() {
        return getRandomChar('a', 26);
    }

    private static char getRandomUpperChar() {
        return getRandomChar('A', 26);
    }

    private static char getRandomDigestChar() {
        return getRandomChar('0', 10);
    }

    private static char getRandomChar(char start, int length) {
        return (char) (start + RANDOM.nextInt(length));
    }

    private static void shuffle(char[] originArr) {
        int length = originArr.length;
        for (int i = 0; i < length; i++) {
            int targetIndex = RANDOM.nextInt(length - 1);
            char temp = originArr[i];
            originArr[i] = originArr[targetIndex];
            originArr[targetIndex] = temp;
        }
    }

    /*private static int getNextIndex(char[] pwd) {
        int length = pwd.length;
        int rd;
        do {
            rd = RANDOM.nextInt(length);
        } while (pwd[rd] != EMPTY);
        System.out.println(rd);
        return rd;
    }*/

    public static class PasswordStrategy {
        private final int minSymbol;
        private final int minLowChar;
        private final int minUpperChar;
        private final int minDigest;
        private final int length;

        public PasswordStrategy() {
            this(1, 1, 1, 1, 6);
        }

        public PasswordStrategy(int minSymbol, int minLowChar, int minUpperChar, int minDigest, int length) {
            this.minSymbol = minSymbol;
            this.minLowChar = minLowChar;
            this.minUpperChar = minUpperChar;
            this.minDigest = minDigest;
            this.length = length;
        }

        public int getMinDigest() {
            return minDigest;
        }

        public int getMinSymbol() {
            return minSymbol;
        }

        public int getMinLowChar() {
            return minLowChar;
        }

        public int getMinUpperChar() {
            return minUpperChar;
        }

        public int getLength() {
            return length;
        }
    }

    public static void main(String[] args) {
        System.out.println(getRandomPassword());
    }

}
