package scratches;

/**
 * @author jy
 */
public class StackLeakTests {

    private static int count = 0;

    public static void call() {
        count++;
        call();
    }

    public static void main(String[] args) {
        try {
            call();
        } catch (Throwable e) {
            System.out.println(count);
        }
    }
}
