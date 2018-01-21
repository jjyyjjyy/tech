package scratches;

/**
 * @author jy
 * @date 2018/01/21
 */
public class JVMParamTest {

    private static int count = 0;

    private static void recursion(long a, long b, long c) {
        long a1=9, a2=9, a3=9, a4=9, a5=9, a6=9, a7=9, a8=9, a9=9, a10=9;
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
