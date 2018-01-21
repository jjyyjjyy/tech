package scrathes;

/**
 * @author jy
 * @date 2018/01/21
 */
public class JVMParamTest {

    private static int count = 0;

    private static void recursion(long a, long b, long c) {
        long a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
        count++;
        recursion(0, 0, 0);
    }

    private static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        // -Xmx256m
//        System.out.println("-Xmx" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m");
        recursion(0,0,0);
    }
}
